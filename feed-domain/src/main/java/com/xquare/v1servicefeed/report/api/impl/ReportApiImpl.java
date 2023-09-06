package com.xquare.v1servicefeed.report.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.configuration.spi.SecuritySpi;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.spi.QueryFeedSpi;
import com.xquare.v1servicefeed.report.Report;
import com.xquare.v1servicefeed.report.api.ReportApi;
import com.xquare.v1servicefeed.report.api.dto.CreateReportDomainRequest;
import com.xquare.v1servicefeed.report.exception.CannotReportMyFeedException;
import com.xquare.v1servicefeed.report.spi.CommandReportSpi;
import com.xquare.v1servicefeed.user.User;
import com.xquare.v1servicefeed.user.spi.FeedUserSpi;
import com.xquare.v1servicefeed.webhook.SlackReport;
import com.xquare.v1servicefeed.webhook.spi.SendWebhookSpi;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@DomainService
public class ReportApiImpl implements ReportApi {

    private final QueryFeedSpi queryFeedSpi;
    private final CommandReportSpi commandReportSpi;
    private final SecuritySpi securitySpi;
    private final SendWebhookSpi sendWebhookSpi;
    private final FeedUserSpi feedUserSpi;

    @Override
    public void saveReport(CreateReportDomainRequest request) {
        Feed feed = queryFeedSpi.queryFeedById(request.getFeedId());
        UUID userId = securitySpi.getCurrentUserId();
        User user = feedUserSpi.queryUserByIds(List.of(userId)).get(0);

        if (userId.equals(feed.getUserId())) {
            throw CannotReportMyFeedException.EXCEPTION;
        }

        commandReportSpi.saveReport(
                Report.builder()
                        .userId(userId)
                        .reportUserId(feed.getUserId())
                        .feedId(feed.getId())
                        .content(request.getContent())
                        .build()
        );

        sendWebhookSpi.sendReportToSlack(
                new SlackReport(feed.getContent(), user.getName(), request.getContent())
        );
    }
}
