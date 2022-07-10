package com.xquare.v1servicefeed.feed.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class WebFeedUuidResponse {

    private final UUID feedUuid;
}
