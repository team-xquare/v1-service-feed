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
    private final LocalDateTime createdAt;
    private final Integer likeCount;

    @QueryProjection
    public FeedListVO(UUID feedId, UUID userId, String content, LocalDateTime createdAt, Integer likeCount) {
        this.feedId = feedId;
        this.userId = userId;
        this.content = content;
        this.createdAt = createdAt;
        this.likeCount = likeCount;
    }
}
