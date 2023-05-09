package com.xquare.v1servicefeed.notification.error;

import com.xquare.v1servicefeed.error.ExceptionProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum NotificationErrorCode implements ExceptionProperty {

    JSON_CONVERT_ERROR(400, "Json Convert Error");

    private final int status;
    private final String message;
}
