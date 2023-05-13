package com.xquare.v1servicefeed.feed.api.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaveFeedEvent {

    private final String type;
}
