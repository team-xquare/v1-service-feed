package com.xquare.v1servicefeed.feedlike.error;

import com.xquare.v1servicefeed.error.ExceptionProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum FeedLikeErrorCode implements ExceptionProperty {

    FEED_LIKE_NOT_FOUND(404, "Feed Like Not Found"),

    USER_ALREADY_CLICKED_SAVE(409, "User Already Clicked Save"),
    USER_ALREADY_CLICKED_CANCEL(409, "User Already Clicked Delete");

    private final int status;
    private final String message;
}
