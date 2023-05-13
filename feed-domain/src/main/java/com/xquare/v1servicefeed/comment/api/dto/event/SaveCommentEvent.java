package com.xquare.v1servicefeed.comment.api.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class SaveCommentEvent {

    private final UUID feedUserId;
    private final UUID feedId;
}
