package com.xquare.v1servicefeed.declaration.api.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CreateDeclarationDomainRequest {
    private UUID feedId;

    private String content;
}
