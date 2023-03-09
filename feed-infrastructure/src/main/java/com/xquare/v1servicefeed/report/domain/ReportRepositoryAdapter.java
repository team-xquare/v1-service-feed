package com.xquare.v1servicefeed.report.domain;

import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import com.xquare.v1servicefeed.report.Report;
import com.xquare.v1servicefeed.report.domain.mapper.ReportMapper;
import com.xquare.v1servicefeed.report.domain.repository.ReportRepository;
import com.xquare.v1servicefeed.report.spi.ReportSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Adapter
public class ReportRepositoryAdapter implements ReportSpi {

    private final ReportMapper reportMapper;
    private final ReportRepository reportRepository;

    @Override
    @Transactional
    public void saveReport(Report report) {
        reportRepository.save(
                reportMapper.domainToEntity(report)
        );
    }
}
