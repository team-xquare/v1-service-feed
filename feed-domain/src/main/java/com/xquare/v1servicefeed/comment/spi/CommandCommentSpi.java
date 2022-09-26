package com.xquare.v1servicefeed.comment.spi;

import com.xquare.v1servicefeed.annotation.Spi;
import com.xquare.v1servicefeed.comment.Comment;

import java.util.UUID;

@Spi
public interface CommandCommentSpi {
    void saveComment(Comment comment);

    Comment findById(UUID commentUuId);

    void deleteComment(Comment comment);

}
