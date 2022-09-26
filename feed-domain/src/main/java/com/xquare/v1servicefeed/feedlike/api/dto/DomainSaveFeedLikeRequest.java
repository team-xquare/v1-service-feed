package com.xquare.v1servicefeed.feedlike.api.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class DomainSaveFeedLikeRequest {
    private final UUID feedId;
    private final UUID userId;
}