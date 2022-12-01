package com.xquare.v1servicefeed.comment.domain.repository;

import com.xquare.v1servicefeed.comment.Comment;
import com.xquare.v1servicefeed.comment.api.dto.request.UpdateCommentDomainRequest;
import com.xquare.v1servicefeed.comment.api.dto.response.CommentDomainElement;
import com.xquare.v1servicefeed.comment.domain.CommentEntity;
import com.xquare.v1servicefeed.comment.domain.mapper.CommentMapper;
import com.xquare.v1servicefeed.comment.exception.CommentNotFoundException;
import com.xquare.v1servicefeed.comment.spi.CommentSpi;
import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import com.xquare.v1servicefeed.configuration.feign.client.UserInfoClient;
import com.xquare.v1servicefeed.configuration.feign.dto.response.UserInfoResponse;
import com.xquare.v1servicefeed.feed.domain.FeedEntity;
import com.xquare.v1servicefeed.feed.domain.repository.FeedRepository;
import com.xquare.v1servicefeed.feed.exception.FeedNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Adapter
public class CommentRepositoryAdapter implements CommentSpi {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final FeedRepository feedRepository;
    private final UserInfoClient userInfoClient;

    @Override
    @Transactional
    public void saveComment(Comment comment) {
        commentRepository.save(
                commentMapper.domainToEntity(comment)
        );
    }

    @Override
    public void deleteCommentById(UUID commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public void updateComment(UpdateCommentDomainRequest request) {
        CommentEntity comment = getCommentById(request.getCommentId());
        commentRepository.save(comment.updateComment(request.getContent(), LocalDateTime.now()));
    }

    @Override
    public void deleteAllCommentByFeedId(UUID feedId) {
        commentRepository.deleteAllById(feedId);
    }

    @Override
    public List<CommentDomainElement> findCommentByFeedId(UUID feedId) {
        FeedEntity feed = feedRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        return commentRepository.findAllByFeed(feed)
                .stream()
                .map(this::commentBuilder)
                .toList();
    }

    private CommentEntity getCommentById(UUID commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> CommentNotFoundException.EXCEPTION);
    }

    private CommentDomainElement commentBuilder(CommentEntity comment) {
        UserInfoResponse response = userInfoClient.getUserInfo(comment.getUserId());

        return CommentDomainElement.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .name(response.getName())
                .profile(response.getProfileFileName())
                .createAt(comment.getCreatedAt())
                .build();
    }
}
