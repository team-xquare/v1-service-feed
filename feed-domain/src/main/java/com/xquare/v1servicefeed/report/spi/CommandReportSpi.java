package com.xquare.v1servicefeed.report.spi;

import com.xquare.v1servicefeed.report.Report;

public interface CommandReportSpi {
    void saveReport(Report report);
}
