package com.xquare.v1servicefeed.comment.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class WebCreateCommentRequest {

    @NotBlank
    private UUID feedUuid;

    @NotBlank
    private String content;
}
