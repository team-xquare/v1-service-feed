package com.xquare.v1servicefeed.feed.domain.repository;

import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.domain.FeedEntity;
import com.xquare.v1servicefeed.feed.domain.mapper.FeedMapper;
import com.xquare.v1servicefeed.feed.exception.FeedNotFoundException;
import com.xquare.v1servicefeed.feed.spi.FeedSpi;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Adapter
public class FeedRepositoryAdapter implements FeedSpi {

    private final FeedMapper feedMapper;
    private final FeedRepository feedRepository;

    @Override
<<<<<<< HEAD
    public void saveFeed(Feed feed) {
        feedRepository.save(feedMapper.domainToEntity(feed));
=======
    public Feed saveFeed(Feed feed) {

        FeedEntity feedEntity = feedRepository.save(feedMapper.domainToEntity(feed));

        return feedMapper.entityToDomain(feedEntity);
>>>>>>> 14-feed-create
    }

    @Override
    public Feed queryFeedById(UUID feedId) {
        return feedMapper.entityToDomain(
                feedRepository.findById(feedId)
                        .orElseThrow(() -> FeedNotFoundException.EXCEPTION)
        );
    }
}
