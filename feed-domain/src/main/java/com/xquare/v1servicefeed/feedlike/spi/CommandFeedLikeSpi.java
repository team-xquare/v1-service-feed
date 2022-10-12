package com.xquare.v1servicefeed.feedlike.spi;


import com.xquare.v1servicefeed.annotation.Spi;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feedlike.FeedLike;

@Spi
public interface CommandFeedLikeSpi {

    void saveFeedLike(FeedLike feedLike, Feed feed);

}
