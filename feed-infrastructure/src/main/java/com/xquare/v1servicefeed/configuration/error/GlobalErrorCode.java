package com.xquare.v1servicefeed.configuration.error;

import com.xquare.v1servicefeed.error.ExceptionProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum GlobalErrorCode implements ExceptionProperty {

    FEIGN_BAD_REQUEST(400, "Feign Bad Request"),
    FEIGN_UN_AUTHORIZED(401, "Feign Unauthorized"),
    FEIGN_FORBIDDEN(403, "Feign Forbidden"),
    FEIGN_EXPIRED_TOKEN(408, "Feign Expired Token"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int status;
    private final String message;

}
