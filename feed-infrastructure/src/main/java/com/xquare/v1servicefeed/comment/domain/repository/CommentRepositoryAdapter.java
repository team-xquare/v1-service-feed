package com.xquare.v1servicefeed.comment.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.xquare.v1servicefeed.comment.Comment;
import com.xquare.v1servicefeed.comment.api.dto.request.UpdateCommentDomainRequest;
import com.xquare.v1servicefeed.comment.domain.CommentEntity;
import com.xquare.v1servicefeed.comment.domain.mapper.CommentMapper;
import com.xquare.v1servicefeed.comment.domain.repository.vo.CommentListVO;
import com.xquare.v1servicefeed.comment.domain.repository.vo.QCommentListVO;
import com.xquare.v1servicefeed.comment.exception.CommentNotFoundException;
import com.xquare.v1servicefeed.comment.spi.CommentSpi;
import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import com.xquare.v1servicefeed.feed.Feed;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.xquare.v1servicefeed.comment.domain.QCommentEntity.commentEntity;
import static com.xquare.v1servicefeed.feed.domain.QFeedEntity.feedEntity;

@RequiredArgsConstructor
@Adapter
public class CommentRepositoryAdapter implements CommentSpi {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final JPAQueryFactory query;

    @Override
    @Transactional
    public void saveComment(Comment comment) {
        commentRepository.save(
                commentMapper.domainToEntity(comment)
        );
    }

    @Override
    @Transactional
    public void deleteCommentById(UUID commentId) {
        CommentEntity comment = getCommentById(commentId);
        comment.delete(true);
    }

    @Override
    public void updateComment(UpdateCommentDomainRequest request) {
        CommentEntity comment = getCommentById(request.getCommentId());
        commentRepository.save(comment.updateComment(request.getContent()));
    }

    @Override
    public Comment queryCommentById(UUID commentId) {
        CommentEntity comment = getCommentById(commentId);
        return commentMapper.entityToDomain(comment);
    }

    @Override
    public List<UUID> queryAllCommentUserIdByFeed(Feed feed) {
        List<CommentEntity> commentList = query
                .selectFrom(commentEntity).distinct()
                .leftJoin(feedEntity)
                .on(feedEntity.id.eq(feed.getId()))
                .where(
                        feedEntity.id.eq(feed.getId()),
                        feedEntity.authorityType.eq("UKN").not(),
                        commentEntity.deleted.eq(false)
                )
                .orderBy(commentEntity.createAt.desc())
                .fetch();

        return commentList.stream()
                .map(CommentEntity::getUserId)
                .toList();
    }

    @Override
    public List<Comment> queryAllCommentByFeed(Feed feed) {
        List<CommentListVO> voList = query
                .select(new QCommentListVO(
                        commentEntity.id,
                        commentEntity.content,
                        commentEntity.updatedAt,
                        commentEntity.userId
                ))
                .from(commentEntity)
                .where(
                        commentEntity.feedEntity.id.eq(feed.getId()),
                        commentEntity.deleted.eq(false)
                )
                .orderBy(commentEntity.createAt.asc())
                .fetch();

        return voList.stream()
                .map(commentListVO -> Comment.builder()
                        .id(commentListVO.getCommentId())
                        .content(commentListVO.getContent())
                        .updatedAt(commentListVO.getUpdatedAt())
                        .userId(commentListVO.getUserId())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public boolean existByUserId(UUID userId) {
        return commentRepository.existsByUserId(userId);
    }

    @Override
    public Long queryCommentCountByFeedId(UUID feedId) {
        return query
                .select(commentEntity.count())
                .from(commentEntity)
                .innerJoin(feedEntity)
                .on(commentEntity.feedEntity.id.eq(feedEntity.id))
                .where(
                        commentEntity.feedEntity.id.eq(feedId)
                                .and(commentEntity.deleted.eq(false))
                                .and(feedEntity.deleted.eq(false))
                ).fetchFirst();
    }

    private CommentEntity getCommentById(UUID commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
    }
}
