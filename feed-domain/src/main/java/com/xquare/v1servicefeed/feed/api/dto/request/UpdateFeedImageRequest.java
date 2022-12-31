package com.xquare.v1servicefeed.feed.api.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class UpdateFeedImageRequest {
    private final UUID feedId;
    private final List<String> attachmentsUrl;
}
