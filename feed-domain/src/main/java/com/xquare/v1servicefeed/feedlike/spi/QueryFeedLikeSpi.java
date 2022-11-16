package com.xquare.v1servicefeed.feedlike.spi;

import com.xquare.v1servicefeed.annotation.Spi;
import com.xquare.v1servicefeed.feedlike.FeedLike;

import java.util.UUID;

@Spi
public interface QueryFeedLikeSpi {
    FeedLike queryFeedLikeById(UUID feedLikeId);

}
