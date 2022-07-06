package com.xquare.v1servicefeed.feedlike;

import com.xquare.v1servicefeed.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@Aggregate
public class FeeLike {

    private final UUID id;

    private UUID feedId;

    private UUID userId;
}
