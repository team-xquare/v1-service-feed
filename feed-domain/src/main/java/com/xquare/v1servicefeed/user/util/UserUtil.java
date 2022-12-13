package com.xquare.v1servicefeed.user.util;

import com.xquare.v1servicefeed.user.exception.ForbiddenUserException;

import java.util.UUID;

public class UserUtil {

    public void checkValidUser(UUID userId, UUID currentUserId) {
        if (!currentUserId.equals(userId)) {
            throw ForbiddenUserException.EXCEPTION;
        }
    }
}
