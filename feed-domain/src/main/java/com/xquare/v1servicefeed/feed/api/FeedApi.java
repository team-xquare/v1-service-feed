package com.xquare.v1servicefeed.feed.api;

import com.xquare.v1servicefeed.annotation.Api;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainUpdateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.response.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Api
public interface FeedApi {
    SaveFeedResponse saveFeed(DomainCreateFeedRequest request);

    void updateFeed(DomainUpdateFeedRequest request);

    void deleteFeedById(UUID feedId);

    FeedWeakElement getFeed(UUID feedId);

    FeedListPageResponse getAllFeed(UUID categoryId, LocalDateTime dateTime, long limit);

    FeedCategoryListResponse getAllCategory();

    FeedListResponse getAllWriterFeed();
}
