package com.xquare.v1servicefeed.report.domain.mapper;

import com.xquare.v1servicefeed.report.Report;
import com.xquare.v1servicefeed.report.domain.ReportEntity;

public interface ReportMapper {

    Report entityToDomain(ReportEntity reportEntity);

    ReportEntity domainToEntity(Report report);
}
