package com.xquare.v1servicefeed.comment.error;

import com.xquare.v1servicefeed.error.ExceptionProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CommentErrorCode implements ExceptionProperty {

    ;

    private final int status;
    private final String message;
}
