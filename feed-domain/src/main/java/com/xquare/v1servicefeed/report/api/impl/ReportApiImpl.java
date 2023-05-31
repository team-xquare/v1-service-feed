package com.xquare.v1servicefeed.report.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.configuration.spi.SecuritySpi;
import com.xquare.v1servicefeed.report.Report;
import com.xquare.v1servicefeed.report.api.ReportApi;
import com.xquare.v1servicefeed.report.api.dto.CreateReportDomainRequest;
import com.xquare.v1servicefeed.report.spi.CommandReportSpi;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.spi.QueryFeedSpi;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@DomainService
public class ReportApiImpl implements ReportApi {

    private final QueryFeedSpi queryFeedSpi;
    private final CommandReportSpi commandReportSpi;
    private final SecuritySpi securitySpi;

    @Override
    public void saveReport(CreateReportDomainRequest request) {
        Feed feed = queryFeedSpi.queryFeedById(request.getFeedId());
        
        commandReportSpi.saveReport(
                Report.builder()
                        .userId(securitySpi.getCurrentUserId())
                        .reportUserId(feed.getUserId())
                        .feedId(feed.getId())
                        .content(request.getContent())
                        .build()
        );
    }
}
