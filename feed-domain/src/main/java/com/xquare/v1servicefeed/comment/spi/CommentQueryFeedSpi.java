package com.xquare.v1servicefeed.comment.spi;

import com.xquare.v1servicefeed.annotation.Spi;
import com.xquare.v1servicefeed.feed.Feed;

import java.util.UUID;

@Spi
public interface CommentQueryFeedSpi {
    Feed queryFeedById(UUID feedId);
}
