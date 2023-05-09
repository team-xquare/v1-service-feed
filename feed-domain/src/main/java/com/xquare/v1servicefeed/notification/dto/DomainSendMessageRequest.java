package com.xquare.v1servicefeed.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class DomainSendMessageRequest {
    private UUID userId;

    private String topic;

    private String content;

    private String threadId;
}
