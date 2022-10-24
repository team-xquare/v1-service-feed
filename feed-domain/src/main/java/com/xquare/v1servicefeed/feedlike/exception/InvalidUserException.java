package com.xquare.v1servicefeed.feedlike.exception;

import com.xquare.v1servicefeed.error.FeedException;
import com.xquare.v1servicefeed.feedlike.error.FeedLikeErrorCode;

public class InvalidUserException extends FeedException {
    public static final FeedException EXCEPTION =
            new InvalidUserException();

    private InvalidUserException() {
        super(FeedLikeErrorCode.INVALID_USER);
    }
}
