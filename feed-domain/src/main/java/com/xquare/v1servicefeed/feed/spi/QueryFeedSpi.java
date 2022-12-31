package com.xquare.v1servicefeed.feed.spi;

import com.xquare.v1servicefeed.annotation.Spi;
import com.xquare.v1servicefeed.feed.Feed;

import java.util.List;
import java.util.UUID;

@Spi
public interface QueryFeedSpi {
    Feed queryFeedById(UUID feedId);

    List<Feed> queryAllFeedByCategory(UUID categoryId);

    List<UUID> queryAllFeedUserIdByCategory(UUID categoryId);
}
