package com.xquare.v1servicefeed.feed.spi;

import com.xquare.v1servicefeed.annotation.Spi;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedList;

import java.util.List;
import java.util.UUID;

@Spi
public interface QueryFeedSpi {
    Feed queryFeedById(UUID feedId);

    List<FeedList> queryAllFeedByCategory(UUID categoryId, long limit, long page);

    List<UUID> queryAllFeedUserIdByCategory(UUID categoryId);

    List<FeedList> queryAllFeedByUserId(UUID userId);
}
