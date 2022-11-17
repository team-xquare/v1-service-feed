package com.xquare.v1servicefeed.feedlike.exception;

import com.xquare.v1servicefeed.error.FeedException;
import com.xquare.v1servicefeed.feedlike.error.FeedLikeErrorCode;

public class FeedLikeNotExistsException extends FeedException {
    public static final FeedException EXCEPTION =
            new FeedLikeNotExistsException();

    private FeedLikeNotExistsException() {
        super(FeedLikeErrorCode.FEED_LIKE_NOT_EXISTS);
    }
}
