package com.xquare.v1servicefeed.feed.spi;

import com.xquare.v1servicefeed.annotation.Spi;

@Spi
public interface FeedSpi extends CommandFeedSpi, QueryFeedSpi {
}
