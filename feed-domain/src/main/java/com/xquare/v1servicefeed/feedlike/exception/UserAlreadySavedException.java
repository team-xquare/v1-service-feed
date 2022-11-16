package com.xquare.v1servicefeed.feedlike.exception;

import com.xquare.v1servicefeed.error.FeedException;
import com.xquare.v1servicefeed.feedlike.error.FeedLikeErrorCode;

public class UserAlreadySavedException extends FeedException {
    public static final FeedException EXCEPTION =
            new UserAlreadySavedException();

    private UserAlreadySavedException() {
        super(FeedLikeErrorCode.USER_ALREADY_SAVED);
    }
}
