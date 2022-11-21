package com.xquare.v1servicefeed.feedlike.error;

import com.xquare.v1servicefeed.error.ExceptionProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum FeedLikeErrorCode implements ExceptionProperty {

    FEED_LIKE_NOT_FOUND(404, "Feed Like Not Found"),

    FEED_LIKE_EXISTS(409, "Feed Like Exists"),
    FEED_LIKE_NOT_EXISTS(404, "Feed Like Not Exists");

    private final int status;
    private final String message;
}
