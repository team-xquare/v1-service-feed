package com.xquare.v1servicefeed.comment.exception;

import com.xquare.v1servicefeed.comment.error.CommentErrorCode;
import com.xquare.v1servicefeed.error.CommentException;
import com.xquare.v1servicefeed.error.ExceptionProperty;
import com.xquare.v1servicefeed.feed.exception.FeedNotFoundException;

public class CommentNotFoundException extends CommentException {

    public static final CommentException EXCEPTION = new CommentNotFoundException();

    public CommentNotFoundException() {
        super(CommentErrorCode.COMMENT_NOT_FOUND);
    }
}
