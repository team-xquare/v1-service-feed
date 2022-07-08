package com.xquare.v1servicefeed.feed.api;

import com.xquare.v1servicefeed.annotation.Api;
import com.xquare.v1servicefeed.feed.api.dto.DomainCreateFeedRequest;

import java.util.UUID;

@Api
public interface CreateFeedApi {
    UUID execute(DomainCreateFeedRequest request);
}
