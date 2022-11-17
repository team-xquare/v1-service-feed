package com.xquare.v1servicefeed.feedlike.exception;

import com.xquare.v1servicefeed.error.FeedException;
import com.xquare.v1servicefeed.feedlike.error.FeedLikeErrorCode;

public class UserAlreadyClickedCancelException extends FeedException {
    public static final FeedException EXCEPTION =
            new UserAlreadyClickedCancelException();

    private UserAlreadyClickedCancelException() {
        super(FeedLikeErrorCode.USER_ALREADY_DELETED);
    }
}
