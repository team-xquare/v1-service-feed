package com.xquare.v1servicefeed.feed.exception;

import com.xquare.v1servicefeed.error.FeedException;
import com.xquare.v1servicefeed.feed.error.FeedErrorCode;

public class FeedNotFoundException extends FeedException {

    public static final FeedException EXCEPTION =
            new FeedNotFoundException();

    private FeedNotFoundException() {
        super(FeedErrorCode.FEED_NOT_FOUND);
    }
}
