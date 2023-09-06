package com.xquare.v1servicefeed.webhook;

import com.xquare.v1servicefeed.webhook.spi.SendWebhookSpi;
import lombok.RequiredArgsConstructor;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SendWebhookAdapter implements SendWebhookSpi {

    private static final String REPORT_MESSAGE = "피드 신고 발생";
    private static final String REPORT_USER_NAME = "신고자";
    private static final String REPORT_CONTENT = "신고 내용";
    private static final String REPORT_FEED_CONTENT = "신고한 게시글 내용";
    private static final String MESSAGE_COLOR = "#9650FA";
    private static final String FALLBACK = "Required plain-text summary of the attachment";

    @Value("${webhook.url}")
    private String webhookUrl;

    @Override
    @Async
    public void sendReportToSlack(SlackReport slackReport) {
        SlackAttachment slackAttachment = createSlackAttachment(slackReport);
        SlackMessage slackMessage = new SlackMessage("").addAttachments(slackAttachment);
        new SlackApi(webhookUrl).call(slackMessage);
    }

    private SlackAttachment createSlackAttachment(SlackReport slackReport) {
        SlackAttachment slackAttachment = new SlackAttachment();
        slackAttachment.setTitle(REPORT_MESSAGE);
        slackAttachment.setText(getErrorReason(slackReport));
        slackAttachment.setColor(MESSAGE_COLOR);
        slackAttachment.setFallback(FALLBACK);
        return slackAttachment;
    }

    private String getErrorReason(SlackReport slackReport) {
        return REPORT_USER_NAME + " : " + slackReport.getUserName()
                + "\n" + REPORT_FEED_CONTENT + " : " + slackReport.getFeedContent()
                + "\n" + REPORT_CONTENT + " : " + slackReport.getReportContent();
    }
}
