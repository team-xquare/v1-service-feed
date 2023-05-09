package com.xquare.v1servicefeed.notification;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import com.xquare.v1servicefeed.notification.dto.DomainSendGroupMessageRequest;
import com.xquare.v1servicefeed.notification.dto.DomainSendMessageRequest;
import com.xquare.v1servicefeed.notification.exception.JsonConvertException;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Adapter
public class NotificationAdapter implements NotificationSpi {

    private final AmazonSQS amazonSQS;
    private final ObjectMapper objectMapper;
    private static final String NOTIFICATION_FIFO = "notification.fifo";
    private static final String NOTIFICATION_GROUP_FIFO = "group-notification.fifo";


    @Override
    public void sendNotification(UUID userId, String topic, String content, String threadId) {
        DomainSendMessageRequest domainSendMessageRequest = new DomainSendMessageRequest(
                userId,
                topic,
                content,
                threadId
        );

        sendSqsMessage(NOTIFICATION_FIFO, convertToJsonString(domainSendMessageRequest));
    }

    @Override
    public void sendGroupNotification(String topic, String content, String threadId) {
        DomainSendGroupMessageRequest domainSendGroupMessageRequest = new DomainSendGroupMessageRequest(
                topic,
                content,
                threadId
        );

        sendSqsMessage(NOTIFICATION_GROUP_FIFO, convertToJsonString(domainSendGroupMessageRequest));
    }

    private void sendSqsMessage(String queueName, String content) {
        String name = amazonSQS.getQueueUrl(queueName).getQueueUrl();
        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(name)
                .withMessageBody(content)
                .withMessageGroupId("feed")
                .withMessageDeduplicationId(UUID.randomUUID().toString())
                .withMessageAttributes(
                        Map.of(
                                "Content-Type", new MessageAttributeValue()
                                        .withDataType("String")
                                        .withStringValue("application/json")
                        )
                );

        amazonSQS.sendMessage(sendMessageRequest);
    }

    private String convertToJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw JsonConvertException.EXCEPTION;
        }
    }
}
