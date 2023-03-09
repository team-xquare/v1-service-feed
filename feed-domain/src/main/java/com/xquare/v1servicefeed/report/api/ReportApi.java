package com.xquare.v1servicefeed.report.api;

import com.xquare.v1servicefeed.annotation.Api;
import com.xquare.v1servicefeed.report.api.dto.CreateReportDomainRequest;

@Api
public interface ReportApi {

    void saveReport(CreateReportDomainRequest request);
}
