package com.xquare.v1servicefeed.feed.spi;

import com.xquare.v1servicefeed.annotation.Spi;
import com.xquare.v1servicefeed.comment.spi.QueryCommentSpi;
import com.xquare.v1servicefeed.feedlike.spi.QueryFeedLikeSpi;

@Spi
public interface FeedSpi extends CommandFeedSpi, QueryCommentSpi, QueryFeedLikeSpi {
}
