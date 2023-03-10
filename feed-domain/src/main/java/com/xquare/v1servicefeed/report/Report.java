package com.xquare.v1servicefeed.report;

import com.xquare.v1servicefeed.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@Aggregate
public class Report {
    private final UUID id;

    private final UUID reportUserId;

    private final String content;

    private final UUID feedId;

    private final UUID userId;

}
