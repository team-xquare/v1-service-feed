package com.xquare.v1servicefeed.feedlike.domain.repository;

import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.domain.FeedEntity;
import com.xquare.v1servicefeed.feed.domain.mapper.FeedMapper;
import com.xquare.v1servicefeed.feedlike.FeedLike;
import com.xquare.v1servicefeed.feedlike.domain.mapper.FeedLikeMapper;
import com.xquare.v1servicefeed.feedlike.spi.FeedLikeSpi;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Adapter
public class FeedLikeRepositoryAdapter implements FeedLikeSpi {

    private final FeedLikeMapper feedLikeMapper;
    private final FeedMapper feedMapper;
    private final FeedLikeRepository feedLikeRepository;

    @Override
    public void saveFeedLike(FeedLike feedLike, Feed feed) {

        FeedEntity feedEntity = feedMapper.domainToEntity(feed);
        feedEntity.plusLikeCount();
        feedLikeRepository.save(feedLikeMapper.domainToEntity(feedLike));
    }

}
