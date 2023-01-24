package com.xquare.v1servicefeed.feedlike.domain.repository;

import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.domain.FeedEntity;
import com.xquare.v1servicefeed.feed.domain.mapper.FeedMapper;
import com.xquare.v1servicefeed.feedlike.FeedLike;
import com.xquare.v1servicefeed.feedlike.domain.FeedLikeEntity;
import com.xquare.v1servicefeed.feedlike.domain.mapper.FeedLikeMapper;
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
        feedLikeRepository.save(feedLikeEntity);
    }

    @Override
    @Transactional
    public void cancelFeedLike(FeedLike feedLike) {
        FeedLikeEntity feedLikeEntity = feedLikeMapper.domainToEntity(feedLike);
        feedLikeRepository.delete(feedLikeEntity);
    }

    @Override
    public boolean existsUser(UUID userId) {
        return feedLikeRepository.existsByUserId(userId);
    }

    @Override
    public FeedLike queryFeedLikeByFeedAndUserId(Feed feed, UUID userId) {
        FeedEntity feedEntity = feedMapper.domainToEntity(feed);
        FeedLikeEntity feedLikeEntity = feedLikeRepository.findFeedLikeEntityByUserIdAndFeedEntity(userId, feedEntity)
                .orElse(null);

        if (feedLikeEntity == null) return null;
        return feedLikeMapper.entityToDomain(feedLikeEntity);
    }
}
