package com.xquare.v1servicefeed.feedlike.api;

import com.xquare.v1servicefeed.annotation.Api;
import com.xquare.v1servicefeed.feedlike.api.dto.SaveFeedLikeDomainRequest;

import java.util.UUID;

@Api
public interface FeedLikeApi {

    void saveFeedLike(SaveFeedLikeDomainRequest request);

    void deleteFeedLike(UUID feedLikeId);
}
