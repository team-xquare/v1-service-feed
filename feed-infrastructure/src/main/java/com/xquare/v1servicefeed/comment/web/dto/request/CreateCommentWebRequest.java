package com.xquare.v1servicefeed.comment.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class CreateCommentWebRequest {

    @NotNull
    private UUID feedUuid;

    @NotBlank
    private String content;
}
