package com.xquare.v1servicefeed.feed.api.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class DomainUpdateFeedRequest {
    private final UUID feedId;
    private final String content;
}
