package com.xquare.v1servicefeed.user.exception;

import com.xquare.v1servicefeed.error.FeedException;
import com.xquare.v1servicefeed.user.error.UserErrorCode;

public class UserNotFoundException extends FeedException {

    public static final FeedException EXCEPTION =
            new UserNotFoundException();

    private UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
