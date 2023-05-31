package com.xquare.v1servicefeed.report.error;

import com.xquare.v1servicefeed.error.ExceptionProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ReportErrorCode implements ExceptionProperty {
    CAN_NOT_REPORT_MY_FEED(401, "Can Not Report My Feed");

    private final int status;
    private final String message;
}
