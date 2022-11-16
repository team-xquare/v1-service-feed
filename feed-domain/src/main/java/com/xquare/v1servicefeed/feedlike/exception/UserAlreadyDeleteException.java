package com.xquare.v1servicefeed.feedlike.exception;

import com.xquare.v1servicefeed.error.FeedException;
import com.xquare.v1servicefeed.feedlike.error.FeedLikeErrorCode;

public class UserAlreadyDeleteException extends FeedException {
    public static final FeedException EXCEPTION =
            new UserAlreadyDeleteException();

    private UserAlreadyDeleteException() {
        super(FeedLikeErrorCode.USER_ALREADY_DELETE);
    }
}
