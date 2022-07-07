package com.xquare.v1servicefeed.comment.api.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class DomainCreateCommnetRequest {

    private final UUID userId;
    private final UUID feedUuid;
    private final String content;
}
