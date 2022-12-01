package com.xquare.v1servicefeed.comment;

import com.xquare.v1servicefeed.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@Aggregate
public class Comment {

    private final UUID id;

    private final String content;

    private final UUID feedId;

    private final UUID userId;

    private final LocalDateTime createAt;
}
