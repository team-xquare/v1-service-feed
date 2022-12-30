package com.xquare.v1servicefeed.feed.api;

import com.xquare.v1servicefeed.annotation.Api;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedImageRequest;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainUpdateFeedImageRequest;

import java.util.UUID;

@Api
public interface FeedImageApi {

    void saveAllFeedImage(DomainCreateFeedImageRequest request);

    void deleteAllFeedImage(UUID feedId);

    void updateFeedImage(DomainUpdateFeedImageRequest request);
}
