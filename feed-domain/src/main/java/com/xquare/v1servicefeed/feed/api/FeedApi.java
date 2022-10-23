package com.xquare.v1servicefeed.feed.api;

import com.xquare.v1servicefeed.annotation.Api;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainUpdateFeedRequest;

import java.util.UUID;

@Api
public interface FeedApi {
    void saveFeed(DomainCreateFeedRequest request);

    void updateFeed(DomainUpdateFeedRequest request);

    void deleteFeedById(UUID feedId);
}
