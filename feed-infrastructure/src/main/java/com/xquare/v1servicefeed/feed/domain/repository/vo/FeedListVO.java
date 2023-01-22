package com.xquare.v1servicefeed.feed.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class FeedListVO {

    private final UUID feedId;
    private final UUID userId;
    private final String content;
    private final String type;
    private final LocalDateTime createdAt;
    private final Long likeCount;
    private final Long commentCount;

    @QueryProjection
    public FeedListVO(UUID feedId, UUID userId, String content, String type, LocalDateTime createdAt, Long likeCount, Long commentCount) {
        this.feedId = feedId;
        this.userId = userId;
        this.content = content;
        this.type = type;
        this.createdAt = createdAt;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
    }
}
