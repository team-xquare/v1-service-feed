package com.xquare.v1servicefeed.comment.api.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class DomainCreateCommentRequest {
    private final UUID feedId;
    private final String content;
}
