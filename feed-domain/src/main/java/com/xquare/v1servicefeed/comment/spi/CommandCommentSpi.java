package com.xquare.v1servicefeed.comment.spi;

import com.xquare.v1servicefeed.annotation.Spi;
import com.xquare.v1servicefeed.comment.Comment;

@Spi
public interface CommandCommentSpi {
    void saveComment(Comment comment);
}
