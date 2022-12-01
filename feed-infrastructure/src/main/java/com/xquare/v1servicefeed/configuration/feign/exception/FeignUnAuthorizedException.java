package com.xquare.v1servicefeed.configuration.feign.exception;

import com.xquare.v1servicefeed.configuration.error.GlobalErrorCode;
import com.xquare.v1servicefeed.error.FeedException;

public class FeignUnAuthorizedException extends FeedException {
    public static final FeedException EXCEPTION = new FeignUnAuthorizedException();

    private FeignUnAuthorizedException() {
        super(GlobalErrorCode.FEIGN_UN_AUTHORIZED);
    }
}
