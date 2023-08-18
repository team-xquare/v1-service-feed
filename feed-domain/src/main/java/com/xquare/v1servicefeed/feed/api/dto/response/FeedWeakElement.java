package com.xquare.v1servicefeed.feed.api.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class FeedWeakElement {

    private final UUID feedId;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final String profile;
    private final String name;
    private final String authorityType;
    private final List<String> attachmentsUrl;
    private final Boolean isMine;
}
