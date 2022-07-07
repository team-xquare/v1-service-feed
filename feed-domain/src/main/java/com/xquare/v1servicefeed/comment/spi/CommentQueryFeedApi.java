package com.xquare.v1servicefeed.comment.spi;

import com.xquare.v1servicefeed.feed.Feed;

import java.util.UUID;

public interface CommentQueryFeedApi {
    Feed queryFeedById(UUID feedId);
}
