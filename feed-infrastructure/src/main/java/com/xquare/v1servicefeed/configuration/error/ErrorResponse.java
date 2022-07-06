package com.xquare.v1servicefeed.configuration.error;

import com.xquare.v1servicefeed.error.ExceptionProperty;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private final int status;
    private final String message;

    public ErrorResponse(ExceptionProperty errorProperty) {
        this.status = errorProperty.getStatus();
        this.message = errorProperty.getMessage();
    }
}
