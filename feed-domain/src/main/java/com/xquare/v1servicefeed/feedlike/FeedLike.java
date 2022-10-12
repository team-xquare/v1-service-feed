package com.xquare.v1servicefeed.feedlike;

import com.xquare.v1servicefeed.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@Aggregate
public class FeedLike {

    private final UUID id;

    private final UUID feedId;

    private final UUID userId;
}
