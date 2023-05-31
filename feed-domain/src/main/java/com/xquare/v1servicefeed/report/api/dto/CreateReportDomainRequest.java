package com.xquare.v1servicefeed.report.api.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CreateReportDomainRequest {
    private UUID feedId;
    private String content;
}
