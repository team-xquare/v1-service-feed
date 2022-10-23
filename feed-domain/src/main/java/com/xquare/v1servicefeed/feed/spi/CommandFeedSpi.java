package com.xquare.v1servicefeed.feed.spi;

import com.xquare.v1servicefeed.annotation.Spi;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainUpdateFeedRequest;

import java.util.UUID;

@Spi
public interface CommandFeedSpi {
    void saveFeed(Feed feed);

    void deleteFeed(Feed feed);

    void updateFeed(DomainUpdateFeedRequest request);
}
