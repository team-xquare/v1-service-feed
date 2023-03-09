package com.xquare.v1servicefeed.report.domain.mapper;

import com.xquare.v1servicefeed.report.Report;
import com.xquare.v1servicefeed.report.domain.ReportEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class ReportMapperImpl implements ReportMapper{
    public Report entityToDomain(ReportEntity reportEntity) {

        return Report.builder()
                .id(reportEntity.getId())
                .userId(reportEntity.getUserId())
                .feedId(reportEntity.getFeedId())
                .content(reportEntity.getContent())
                .build();
    }

    public ReportEntity domainToEntity(Report report) {

        return ReportEntity.builder()
                .id(report.getId())
                .userId(report.getUserId())
                .feedId(report.getFeedId())
                .content(report.getContent())
                .build();
    }
}
