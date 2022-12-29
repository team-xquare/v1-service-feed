package com.xquare.v1servicefeed.feed.api;

import com.xquare.v1servicefeed.annotation.Api;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedImageRequest;

import java.util.UUID;

@Api
public interface FeedImageApi {

    void saveAllFeedImage(DomainCreateFeedImageRequest request);

    void deleteAllFeedImage(UUID feedId);
}
