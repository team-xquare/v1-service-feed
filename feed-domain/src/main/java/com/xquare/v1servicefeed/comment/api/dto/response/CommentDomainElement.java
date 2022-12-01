package com.xquare.v1servicefeed.comment.api.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class CommentDomainElement {

    private final UUID commentId;
    private final String content;
    private final String name;
    private final String profile;
    private final LocalDateTime updatedAt;
}
