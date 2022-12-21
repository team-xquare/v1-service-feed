package com.xquare.v1servicefeed.user.error;

import com.xquare.v1servicefeed.error.ExceptionProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserErrorCode implements ExceptionProperty {

    FORBIDDEN_USER(403, "Forbidden User");

    private final int status;
    private final String message;
}
