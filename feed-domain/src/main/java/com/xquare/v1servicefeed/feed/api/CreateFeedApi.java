package com.xquare.v1servicefeed.feed.api;

import com.xquare.v1servicefeed.annotation.Api;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedUuidResponse;

@Api
public interface CreateFeedApi {
    FeedUuidResponse execute(DomainCreateFeedRequest request);
}
