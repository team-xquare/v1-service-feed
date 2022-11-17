package com.xquare.v1servicefeed.feedlike.exception;

import com.xquare.v1servicefeed.error.FeedException;
import com.xquare.v1servicefeed.feedlike.error.FeedLikeErrorCode;

public class UserAlreadyClickedSaveException extends FeedException {
    public static final FeedException EXCEPTION =
            new UserAlreadyClickedSaveException();

    private UserAlreadyClickedSaveException() {
        super(FeedLikeErrorCode.USER_ALREADY_CLICKED_SAVE);
    }
}
