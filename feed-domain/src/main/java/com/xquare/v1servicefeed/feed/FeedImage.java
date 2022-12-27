package com.xquare.v1servicefeed.feed;

import com.xquare.v1servicefeed.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@Aggregate
public class FeedImage {

    private final Integer order;

    private final UUID feedId;

    private final String filePath;
}
