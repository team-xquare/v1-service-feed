package com.xquare.v1servicefeed.feed.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import java.util.List;

@Getter
@NoArgsConstructor
public class WebUpdateFeedRequest {

    @NotBlank
    @Max(65535)
    private String content;

    @NotBlank
    private List<String> attachmentsUrl;
}
