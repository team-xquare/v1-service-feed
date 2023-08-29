package com.xquare.v1servicefeed.comment.domain.mapper;

import com.xquare.v1servicefeed.comment.Comment;
import com.xquare.v1servicefeed.comment.domain.CommentEntity;
import com.xquare.v1servicefeed.feed.domain.FeedEntity;
import com.xquare.v1servicefeed.feed.domain.repository.FeedRepository;
import com.xquare.v1servicefeed.feed.exception.FeedNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CommentMapper {

    private final FeedRepository feedRepository;

    public Comment entityToDomain(CommentEntity commentEntity) {

        return Comment.builder()
                .id(commentEntity.getId())
                .content(commentEntity.getContent())
                .feedId(commentEntity.getFeedEntity().getId())
                .userId(commentEntity.getUserId())
                .createAt(commentEntity.getCreateAt())
                .updatedAt(commentEntity.getUpdatedAt())
                .deleted(false)
                .build();
    }

    public CommentEntity domainToEntity(Comment comment) {
        FeedEntity feedEntity = feedRepository.findById(comment.getFeedId())
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        return CommentEntity.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .feedEntity(feedEntity)
                .userId(comment.getUserId())
                .createAt(comment.getCreateAt())
                .updatedAt(comment.getUpdatedAt())
                .deleted(false)
                .build();
    }
}
