package com.xquare.v1servicefeed.user.exception;

import com.xquare.v1servicefeed.error.FeedException;
import com.xquare.v1servicefeed.user.error.UserErrorCode;

public class ForbiddenUserException extends FeedException {

    public static final FeedException EXCEPTION =
            new ForbiddenUserException();

    private ForbiddenUserException() {
        super(UserErrorCode.FORBIDDEN_USER);
    }
}
