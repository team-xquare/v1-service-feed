package com.xquare.v1servicefeed.feign;

import com.xquare.v1servicefeed.feign.exception.FeignBadRequestException;
import com.xquare.v1servicefeed.feign.exception.FeignForbiddenException;
import com.xquare.v1servicefeed.feign.exception.FeignServerError;
import com.xquare.v1servicefeed.feign.exception.FeignUnAuthorizedException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400) {
            switch (response.status()) {
                case 400 -> throw FeignBadRequestException.EXCEPTION;
                case 401 -> throw FeignUnAuthorizedException.EXCEPTION;
                case 403 -> throw FeignForbiddenException.EXCEPTION;
                default -> throw FeignServerError.EXCEPTION;
            }
        }
        return FeignException.errorStatus(methodKey, response);
    }
}
