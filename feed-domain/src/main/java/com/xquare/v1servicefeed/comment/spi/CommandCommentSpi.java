package com.xquare.v1servicefeed.comment.spi;

import com.xquare.v1servicefeed.comment.Comment;

public interface CommandCommentSpi {
    void saveComment(Comment comment);
}
