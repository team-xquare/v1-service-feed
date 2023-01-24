package com.xquare.v1servicefeed.feedlike.api;

import com.xquare.v1servicefeed.annotation.Api;

import java.util.UUID;

@Api
public interface FeedLikeApi {

    void saveFeedLike(UUID feedId);

    void cancelFeedLike(UUID feedId);
}
