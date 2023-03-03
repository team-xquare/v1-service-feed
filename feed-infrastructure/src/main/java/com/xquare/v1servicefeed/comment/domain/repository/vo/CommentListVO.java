package com.xquare.v1servicefeed.comment.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class CommentListVO {

    private final UUID commentId;
    private final String content;
    private final LocalDateTime updatedAt;
    private final UUID userId;

    @QueryProjection
    public CommentListVO(UUID commentId, String content, LocalDateTime updatedAt, UUID userId) {
        this.commentId = commentId;
        this.content = content;
        this.updatedAt = updatedAt;
        this.userId = userId;
    }
}
