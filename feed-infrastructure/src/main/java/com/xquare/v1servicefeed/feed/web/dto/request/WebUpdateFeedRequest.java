package com.xquare.v1servicefeed.feed.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class WebUpdateFeedRequest {

    @NotBlank
    @Size(min = 1, max = 255)
    private String title;

    @NotBlank
    @Size(min = 1, max = 65535)
    private String content;
}
