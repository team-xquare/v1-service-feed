package com.xquare.v1servicefeed.feed.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class DomainFeedUuidResponse {

    private final UUID feedUuid;
}
