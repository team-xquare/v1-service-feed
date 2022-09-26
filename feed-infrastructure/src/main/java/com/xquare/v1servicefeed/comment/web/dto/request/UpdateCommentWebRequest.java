package com.xquare.v1servicefeed.comment.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UpdateCommentWebRequest {

    @NotBlank
    private String content;
}
