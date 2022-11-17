package com.xquare.v1servicefeed.feedlike.exception;

import com.xquare.v1servicefeed.error.FeedException;
import com.xquare.v1servicefeed.feedlike.error.FeedLikeErrorCode;

public class UserAlreadyDeletedException extends FeedException {
    public static final FeedException EXCEPTION =
            new UserAlreadyDeletedException();

    private UserAlreadyDeletedException() {
        super(FeedLikeErrorCode.USER_ALREADY_DELETED);
    }
}
