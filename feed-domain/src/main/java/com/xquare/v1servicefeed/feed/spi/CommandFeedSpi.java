package com.xquare.v1servicefeed.feed.spi;

import com.xquare.v1servicefeed.annotation.Spi;
import com.xquare.v1servicefeed.feed.Feed;

@Spi
public interface CommandFeedSpi {
    Feed saveFeed(Feed feed);
}
