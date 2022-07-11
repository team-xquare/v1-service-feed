package com.xquare.v1servicefeed.comment.exception;

import com.xquare.v1servicefeed.comment.error.CommentErrorCode;
import com.xquare.v1servicefeed.error.FeedException;

public class CommentNotFoundException extends FeedException {

    public static final FeedException EXCEPTION = new CommentNotFoundException();

    public CommentNotFoundException() {
        super(CommentErrorCode.COMMENT_NOT_FOUND);
    }
}
