package com.xquare.v1servicefeed.feed.spi;

import com.xquare.v1servicefeed.annotation.Spi;
import com.xquare.v1servicefeed.comment.spi.QueryCommentSpi;

@Spi
public interface FeedSpi extends CommandFeedSpi, QueryCommentSpi, QueryFeedSpi {
}
