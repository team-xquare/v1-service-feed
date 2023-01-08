package com.xquare.v1servicefeed.feed.api.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class DomainCreateFeedRequest {

    private final String title;
    private final String content;
    private final UUID categoryId;
}
