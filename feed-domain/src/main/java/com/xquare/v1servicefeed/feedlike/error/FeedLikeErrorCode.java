package com.xquare.v1servicefeed.feedlike.error;

import com.xquare.v1servicefeed.error.ExceptionProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum FeedLikeErrorCode implements ExceptionProperty {

    FEED_LIKE_NOT_FOUND(404, "Feed Like Not Found"),

    USER_ALREADY_SAVED(409, "User Already Saved"),
    USER_ALREADY_DELETED(409, "User Already Deleted");

    private final int status;
    private final String message;
}
