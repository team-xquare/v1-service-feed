package com.xquare.v1servicefeed.comment.error;

import com.xquare.v1servicefeed.error.ExceptionProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum CommentErrorCode implements ExceptionProperty {

    COMMENT_NOT_FOUND(404, "Comment not Found");

    private final int status;
    private final String message;
}
