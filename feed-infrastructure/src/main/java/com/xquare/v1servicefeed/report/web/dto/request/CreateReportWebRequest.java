package com.xquare.v1servicefeed.report.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class CreateReportWebRequest {

    @NotNull
    private UUID feedId;

    @NotBlank
    private String content;
}
