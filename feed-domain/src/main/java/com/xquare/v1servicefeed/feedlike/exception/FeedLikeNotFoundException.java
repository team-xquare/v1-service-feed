package com.xquare.v1servicefeed.feedlike.exception;

import com.xquare.v1servicefeed.error.FeedException;
import com.xquare.v1servicefeed.feedlike.error.FeedLikeErrorCode;

public class FeedLikeNotFoundException extends FeedException {
    public static final FeedException EXCEPTION =
            new FeedLikeNotFoundException();

    private FeedLikeNotFoundException() {
        super(FeedLikeErrorCode.FEED_LIKE_NOT_FOUND);
    }
}
