package com.xquare.v1servicefeed.feed.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class FeedUuidResponse {

    private final UUID feedUuid;
}
