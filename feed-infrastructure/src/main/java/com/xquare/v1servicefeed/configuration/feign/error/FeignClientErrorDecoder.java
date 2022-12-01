package com.xquare.v1servicefeed.configuration.feign.error;

import com.xquare.v1servicefeed.configuration.feign.exception.*;
import feign.FeignException.FeignClientException;
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
                case 419 -> throw FeignExpiredTokenException.EXCEPTION;
                default -> throw FeignException.EXCEPTION;
            }
        }
        return FeignClientException.errorStatus(methodKey, response);
    }
}
