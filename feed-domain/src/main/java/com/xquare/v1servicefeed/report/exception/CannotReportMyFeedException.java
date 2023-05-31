package com.xquare.v1servicefeed.report.exception;

import com.xquare.v1servicefeed.error.FeedException;
import com.xquare.v1servicefeed.report.error.ReportErrorCode;

public class CannotReportMyFeedException extends FeedException {
    public static final FeedException EXCEPTION = new CannotReportMyFeedException();

    private CannotReportMyFeedException() {
        super(ReportErrorCode.CAN_NOT_REPORT_MY_FEED);
    }
}
