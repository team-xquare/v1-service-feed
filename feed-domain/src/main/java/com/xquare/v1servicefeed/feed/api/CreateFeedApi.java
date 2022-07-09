package com.xquare.v1servicefeed.feed.api;

import com.xquare.v1servicefeed.annotation.Api;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.api.dto.DomainCreateFeedRequest;

@Api
public interface CreateFeedApi {
    Feed execute(DomainCreateFeedRequest request);
}
