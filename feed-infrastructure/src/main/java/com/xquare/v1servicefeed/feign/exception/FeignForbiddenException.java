package com.xquare.v1servicefeed.feign.exception;

import com.xquare.v1servicefeed.configuration.error.GlobalErrorCode;
import com.xquare.v1servicefeed.error.FeedException;

public class FeignForbiddenException extends FeedException {

    public static final FeedException EXCEPTION = new FeignForbiddenException();

    private FeignForbiddenException() {
        super(GlobalErrorCode.FEIGN_FORBIDDEN);
    }
}
