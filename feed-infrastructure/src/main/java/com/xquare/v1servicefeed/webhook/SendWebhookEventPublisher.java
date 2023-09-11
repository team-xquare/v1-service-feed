package com.xquare.v1servicefeed.webhook;

import com.xquare.v1servicefeed.webhook.spi.SendWebhookSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SendWebhookEventPublisher implements SendWebhookSpi {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void sendReportToSlack(SlackReport slackReport) {
        applicationEventPublisher.publishEvent(slackReport);
    }
}
