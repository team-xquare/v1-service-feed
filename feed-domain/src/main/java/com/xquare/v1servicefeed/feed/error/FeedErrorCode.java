package com.xquare.v1servicefeed.feed.error;

import com.xquare.v1servicefeed.error.ExceptionProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum FeedErrorCode implements ExceptionProperty {

    FEED_NOT_FOUND(404, "Feed Not Found");

    private final int status;
    private final String message;
}
