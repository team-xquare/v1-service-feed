package com.xquare.v1servicefeed.feedlike.domain.repository;

import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.domain.FeedEntity;
import com.xquare.v1servicefeed.feed.domain.mapper.FeedMapper;
import com.xquare.v1servicefeed.feedlike.FeedLike;
import com.xquare.v1servicefeed.feedlike.domain.FeedLikeEntity;
import com.xquare.v1servicefeed.feedlike.domain.mapper.FeedLikeMapper;
import com.xquare.v1servicefeed.feedlike.exception.FeedLikeNotFoundException;
import com.xquare.v1servicefeed.feedlike.spi.FeedLikeSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Adapter
public class FeedLikeRepositoryAdapter implements FeedLikeSpi {

    private final FeedLikeMapper feedLikeMapper;
    private final FeedMapper feedMapper;
    private final FeedLikeRepository feedLikeRepository;

    @Override
    @Transactional
    public void saveFeedLike(FeedLike feedLike) {

        FeedLikeEntity feedLikeEntity = feedLikeMapper.domainToEntity(feedLike);
        feedLikeEntity.plusLikeCount();
        feedLikeRepository.save(feedLikeEntity);
    }

    @Override
    @Transactional
    public void cancelFeedLike(FeedLike feedLike) {
        FeedLikeEntity feedLikeEntity = feedLikeMapper.domainToEntity(feedLike);
        feedLikeEntity.minusLikeCount();
        feedLikeRepository.delete(feedLikeEntity);
    }

    @Override
    public boolean existsUser(UUID userId) {
        return feedLikeRepository.existsByUserId(userId);
    }

    @Override
    public FeedLike queryFeedLikeById(UUID feedLikeId) {
        return feedLikeMapper.entityToDomain(getFeedLikeEntityById(feedLikeId));
    }

    @Override
    public FeedLike queryFeedLikeByFeed(Feed feed) {
        FeedLikeEntity feedLikeEntity = getFeedLikeEntityByFeed(feed);
        return feedLikeMapper.entityToDomain(feedLikeEntity);
    }

    private FeedLikeEntity getFeedLikeEntityById(UUID feedLikeId) {
        return feedLikeRepository.findById(feedLikeId)
                .orElseThrow(() -> FeedLikeNotFoundException.EXCEPTION);
    }

    private FeedLikeEntity getFeedLikeEntityByFeed(Feed feed) {
        FeedEntity feedEntity = feedMapper.domainToEntity(feed);

        return feedLikeRepository.findByFeed(feedEntity)
                .orElseThrow(() -> FeedLikeNotFoundException.EXCEPTION);
    }
}
