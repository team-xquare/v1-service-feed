package com.xquare.v1servicefeed.configuration.feign.exception;

import com.xquare.v1servicefeed.configuration.error.GlobalErrorCode;
import com.xquare.v1servicefeed.error.FeedException;

public class FeignBadRequestException extends FeedException {
    public static final FeedException EXCEPTION = new FeignBadRequestException();

    private FeignBadRequestException() {
        super(GlobalErrorCode.FEIGN_BAD_REQUEST);
    }
}
