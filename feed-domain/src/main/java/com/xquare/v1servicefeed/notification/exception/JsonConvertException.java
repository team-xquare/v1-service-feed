package com.xquare.v1servicefeed.notification.exception;

import com.xquare.v1servicefeed.error.FeedException;
import com.xquare.v1servicefeed.notification.error.NotificationErrorCode;

public class JsonConvertException extends FeedException {

    public static final FeedException EXCEPTION =
            new JsonConvertException();

    private JsonConvertException() {
        super(NotificationErrorCode.JSON_CONVERT_ERROR);
    }
}
