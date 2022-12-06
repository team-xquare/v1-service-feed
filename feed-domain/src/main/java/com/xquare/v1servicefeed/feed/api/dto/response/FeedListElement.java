package com.xquare.v1servicefeed.feed.api.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class FeedListElement {

    private final UUID feedId;
    private final String content;
    private final LocalDateTime createdAt;
    private final String profile;
    private final Integer likeCount;
}
