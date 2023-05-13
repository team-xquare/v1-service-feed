package com.xquare.v1servicefeed.feedlike.api.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class SaveFeedLikeEvent {

    private final UUID feedUserId;
    private final UUID feedId;
}
