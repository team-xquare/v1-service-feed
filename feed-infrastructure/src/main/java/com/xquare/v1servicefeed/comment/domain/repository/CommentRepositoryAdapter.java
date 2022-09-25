package com.xquare.v1servicefeed.comment.domain.repository;

import com.xquare.v1servicefeed.comment.Comment;
import com.xquare.v1servicefeed.comment.api.dto.request.DomainUpdateCommentRequest;
import com.xquare.v1servicefeed.comment.domain.CommentEntity;
import com.xquare.v1servicefeed.comment.domain.mapper.CommentMapper;
import com.xquare.v1servicefeed.comment.exception.CommentNotFoundException;
import com.xquare.v1servicefeed.comment.spi.CommandCommentSpi;
import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import com.xquare.v1servicefeed.feed.domain.FeedEntity;
import com.xquare.v1servicefeed.feed.exception.FeedNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Adapter
public class CommentRepositoryAdapter implements CommandCommentSpi {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public void saveComment(Comment comment) {
        commentRepository.save(
                commentMapper.domainToEntity(comment)
        );
    }

    @Override
    public Comment findById(UUID commentUuid) {
        return commentMapper.entityToDomain(
                commentRepository.findById(commentUuid)
                        .orElseThrow(() -> CommentNotFoundException.EXCEPTION)
        );
    }

    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete(
                commentMapper.domainToEntity(comment)
        );
    }

    @Override
    public void updateComment(DomainUpdateCommentRequest request) {
        CommentEntity comment = getCommentById(request.getCommentId());

        comment.updateComment(request.getContent());
    }

    private CommentEntity getCommentById(UUID commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
    }

}
