package com.xquare.v1servicefeed.feedlike.exception;

import com.xquare.v1servicefeed.error.FeedException;
import com.xquare.v1servicefeed.feedlike.error.FeedLikeErrorCode;

public class FeedLikeExistsException extends FeedException {
    public static final FeedException EXCEPTION =
            new FeedLikeExistsException();

    private FeedLikeExistsException() {
        super(FeedLikeErrorCode.FEED_LIKE_EXISTS);
    }
}
