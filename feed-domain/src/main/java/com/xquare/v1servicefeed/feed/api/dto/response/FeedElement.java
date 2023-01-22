package com.xquare.v1servicefeed.feed.api.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

import java.util.List;

@Getter
@Builder
public class FeedElement {

    private final UUID feedId;
    private final String content;
    private final LocalDateTime createdAt;
    private final String profile;
    private final String name;
    private final String type;
    private final Long likeCount;
    private final Long commentCount;
    private final Boolean isMine;
    private final Boolean isLike;
    private final List<String> attachmentsUrl;
}
