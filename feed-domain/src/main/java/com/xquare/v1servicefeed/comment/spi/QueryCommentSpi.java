package com.xquare.v1servicefeed.comment.spi;

import com.xquare.v1servicefeed.annotation.Spi;
import com.xquare.v1servicefeed.comment.Comment;
import com.xquare.v1servicefeed.feed.Feed;

import java.util.List;
import java.util.UUID;

@Spi
public interface QueryCommentSpi {

    Comment queryCommentById(UUID commentId);

    List<UUID> queryAllCommentUserIdByFeed(Feed feed);

    List<Comment> queryAllCommentByFeed(Feed feed);

    boolean existByUserId(UUID userId);
}