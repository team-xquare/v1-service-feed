package com.xquare.v1servicefeed.user.exception;

import com.xquare.v1servicefeed.error.FeedException;
import com.xquare.v1servicefeed.user.error.UserErrorCode;

public class InvalidRoleException extends FeedException{


    public static final FeedException EXCEPTION =
            new InvalidRoleException();

    private InvalidRoleException() {
        super(UserErrorCode.INVALID_ROLE);
    }
}
