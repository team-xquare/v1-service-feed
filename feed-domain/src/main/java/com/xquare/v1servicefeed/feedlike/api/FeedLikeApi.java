package com.xquare.v1servicefeed.feedlike.api;

import com.xquare.v1servicefeed.annotation.Api;
import com.xquare.v1servicefeed.feedlike.api.dto.DomainSaveFeedLikeRequest;

@Api
public interface FeedLikeApi {

    void saveFeedLike(DomainSaveFeedLikeRequest request);
}
