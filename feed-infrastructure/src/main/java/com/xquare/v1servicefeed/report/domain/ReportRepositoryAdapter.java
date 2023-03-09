package com.xquare.v1servicefeed.report.domain;

import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import com.xquare.v1servicefeed.report.Report;
import com.xquare.v1servicefeed.report.domain.mapper.ReportMapperImpl;
import com.xquare.v1servicefeed.report.domain.repository.ReportRepository;
import com.xquare.v1servicefeed.report.spi.ReportSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Adapter
public class ReportRepositoryAdapter implements ReportSpi {

    private final ReportMapperImpl reportMapperImpl;
    private final ReportRepository reportRepository;

    @Override
    @Transactional
    public void saveDeclaration(Report declaration) {
        reportRepository.save(
                reportMapperImpl.domainToEntity(declaration)
        );
    }
}
