package com.xquare.v1servicefeed.feed.api;

import com.xquare.v1servicefeed.annotation.Api;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedRequest;
<<<<<<< HEAD

@Api
public interface CreateFeedApi {
    void execute(DomainCreateFeedRequest request);
=======
import com.xquare.v1servicefeed.feed.api.dto.response.FeedUuidResponse;

@Api
public interface CreateFeedApi {
    FeedUuidResponse execute(DomainCreateFeedRequest request);
>>>>>>> 14-feed-create
}
