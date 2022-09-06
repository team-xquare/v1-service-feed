package com.xquare.v1servicefeed.feed.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class WebCreateFeedRequest {

    @NotBlank
    @Max(255)
    private String title;

    @NotBlank
    @Max(65535)
    private String content;

    @NotBlank
    @Max(6)
    private String category;
}