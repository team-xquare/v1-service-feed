package com.xquare.v1servicefeed.feed.spi;

import com.xquare.v1servicefeed.annotation.Spi;
import com.xquare.v1servicefeed.feed.Feed;

import java.util.List;

@Spi
public interface QueryFeedImageSpi {
    List<String> queryAllAttachmentsUrl(Feed feed);
}
