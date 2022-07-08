package com.xquare.v1servicefeed.feed.domain.repository;

import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import com.xquare.v1servicefeed.feed.Feed;
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
    public Feed saveFeed(Feed feed) {
        feedRepository.save(
                feedMapper.domainToEntity(feed)
        );
        return feed;
    }

    @Override
    public Feed queryFeedById(UUID feedId) {
        return feedMapper.entityToDomain(
                feedRepository.findById(feedId)
                        .orElseThrow(() -> FeedNotFoundException.EXCEPTION)
        );
    }
}
