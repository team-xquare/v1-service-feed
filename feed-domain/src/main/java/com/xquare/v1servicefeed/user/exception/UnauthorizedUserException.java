package com.xquare.v1servicefeed.user.exception;

import com.xquare.v1servicefeed.error.FeedException;
import com.xquare.v1servicefeed.user.error.UserErrorCode;

public class UnauthorizedUserException extends FeedException {

    public static final FeedException EXCEPTION =
            new UnauthorizedUserException();

    private UnauthorizedUserException() {
        super(UserErrorCode.UNAUTHORIZED_USER);
    }
}
