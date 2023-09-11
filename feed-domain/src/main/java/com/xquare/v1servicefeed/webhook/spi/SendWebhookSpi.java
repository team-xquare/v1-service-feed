package com.xquare.v1servicefeed.webhook.spi;

import com.xquare.v1servicefeed.annotation.Spi;
import com.xquare.v1servicefeed.webhook.SlackReport;

@Spi
public interface SendWebhookSpi {

    void sendReportToSlack(SlackReport slackReport);
}
