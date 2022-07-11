package com.xquare.v1servicefeed.feed.domain.repository;

import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainUpdateFeedRequest;
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
    public void saveFeed(Feed feed) {

        feedRepository.save(feedMapper.domainToEntity(feed));
    }

    @Override
    public void updateFeed(UUID feedId, DomainUpdateFeedRequest request) {

        FeedEntity feed = getFeedEntityById(feedId);

        feed.updateFeed(request.getTitle(), request.getContent());
    }

    @Override
    public Feed queryFeedById(UUID feedId) {
        return feedMapper.entityToDomain(getFeedEntityById(feedId));
    }

    private FeedEntity getFeedEntityById(UUID feedId) {
        return feedRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);
    }
}
