package com.xquare.v1servicefeed.user.error;

import com.xquare.v1servicefeed.error.ExceptionProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserErrorCode implements ExceptionProperty {

    UNAUTHORIZED_USER(403, "Unauthorized User");

    private final int status;
    private final String message;
}
