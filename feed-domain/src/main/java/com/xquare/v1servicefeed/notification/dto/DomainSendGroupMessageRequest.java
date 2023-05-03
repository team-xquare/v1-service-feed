package com.xquare.v1servicefeed.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DomainSendGroupMessageRequest {

    private String topic;

    private String content;

    private String threadId;
}
