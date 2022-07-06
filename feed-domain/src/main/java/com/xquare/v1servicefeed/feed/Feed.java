package com.xquare.v1servicefeed.feed;

import com.xquare.v1servicefeed.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@Aggregate
public class Feed {

    private final UUID id;

    private final UUID userId;

    private final String title;

    private final String content;

    private final LocalDateTime createdAt;

    private final Integer likeCount;

    private final String category;
}
