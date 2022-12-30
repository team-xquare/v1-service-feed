package com.xquare.v1servicefeed.feed.api;

import com.xquare.v1servicefeed.annotation.Api;
import com.xquare.v1servicefeed.feed.api.dto.request.CreateFeedImageRequest;
import com.xquare.v1servicefeed.feed.api.dto.request.UpdateFeedImageRequest;

import java.util.UUID;

@Api
public interface FeedImageApi {

    void saveAllFeedImage(CreateFeedImageRequest request);

    void deleteAllFeedImage(UUID feedId);

    void updateFeedImage(UpdateFeedImageRequest request);
}
