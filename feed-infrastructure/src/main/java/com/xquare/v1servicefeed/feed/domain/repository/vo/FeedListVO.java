package com.xquare.v1servicefeed.feed.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class FeedListVO {

    private final UUID feedId;
    private final UUID userId;
    private final String title;
    private final String content;
    private final String authorityType;
    private final LocalDateTime createdAt;
    private final Long likeCount;

    @QueryProjection
    public FeedListVO(UUID feedId, UUID userId, String title, String content, String authorityType, LocalDateTime createdAt, Long likeCount) {
        this.feedId = feedId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.authorityType = authorityType;
        this.createdAt = createdAt;
        this.likeCount = likeCount;
    }
}
