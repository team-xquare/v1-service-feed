package com.xquare.v1servicefeed.feign.exception;

import com.xquare.v1servicefeed.configuration.error.GlobalErrorCode;
import com.xquare.v1servicefeed.error.FeedException;

public class FeignServerError extends FeedException {

    public static final FeedException EXCEPTION = new FeignServerError();

    private FeignServerError() {
        super(GlobalErrorCode.FEIGN_SERVER_ERROR);
    }
}
