package com.xquare.v1servicefeed.configuration.feign.exception;

import com.xquare.v1servicefeed.configuration.error.GlobalErrorCode;
import com.xquare.v1servicefeed.error.FeedException;

public class FeignExpiredTokenException extends FeedException {
    public static final FeedException EXCEPTION = new FeignExpiredTokenException();

    private FeignExpiredTokenException() {
        super(GlobalErrorCode.FEIGN_EXPIRED_TOKEN);
    }
}
