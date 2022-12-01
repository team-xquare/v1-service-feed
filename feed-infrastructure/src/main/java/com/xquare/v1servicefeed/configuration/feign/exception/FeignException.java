package com.xquare.v1servicefeed.configuration.feign.exception;

import com.xquare.v1servicefeed.configuration.error.GlobalErrorCode;
import com.xquare.v1servicefeed.error.FeedException;

public class FeignException extends FeedException {
    public static final FeedException EXCEPTION =
            new FeignException();

    private FeignException() {
        super(GlobalErrorCode.FEIGN_CLIENT_ERROR);
    }
}
