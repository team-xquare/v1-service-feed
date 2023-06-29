package com.xquare.v1servicefeed.feed.api.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class FeedList {
    private final UUID feedId;
    private final UUID userId;
    private final String title;
    private final String content;
    private final String authorityType;
    private final LocalDateTime createdAt;
    private final Long likeCount;
    private final Long commentCount;
}
