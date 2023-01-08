package com.xquare.v1servicefeed.feed.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class WebCreateFeedRequest {

    @NotBlank
    @Max(255)
    private String title;

    @NotBlank
    @Max(65535)
    private String content;

    @NotNull
    private UUID categoryId;
}