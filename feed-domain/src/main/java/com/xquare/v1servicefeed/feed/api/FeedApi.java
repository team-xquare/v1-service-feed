package com.xquare.v1servicefeed.feed.api;

import com.xquare.v1servicefeed.annotation.Api;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainUpdateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedCategoryListResponse;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedListResponse;
import com.xquare.v1servicefeed.feed.api.dto.response.SaveFeedResponse;

import java.util.UUID;

@Api
public interface FeedApi {
    SaveFeedResponse saveFeed(DomainCreateFeedRequest request);

    void updateFeed(DomainUpdateFeedRequest request);

    void deleteFeedById(UUID feedId);

    FeedListResponse getAllFeed(UUID categoryId, long limit, long page);

    FeedCategoryListResponse getAllCategory();

    FeedListResponse getAllWriterFeed();
}
