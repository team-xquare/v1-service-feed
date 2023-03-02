package com.xquare.v1servicefeed.feedlike.domain.repository;

import com.xquare.v1servicefeed.configuration.annotation.Adapter;
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
    @Transactional
    public void deleteFeedLikeByFeedId(UUID feedId) {
        feedLikeRepository.deleteAllByFeedEntityId(feedId);
    }

    @Override
    public boolean existsByUserIdAndFeedId(UUID userId, UUID feedEntityId) {
        return feedLikeRepository.existsByUserIdAndFeedEntityId(userId, feedEntityId);
    }

    @Override
    public FeedLike queryFeedLikeByFeedIdAndUserId(UUID feedId, UUID userId) {
        FeedLikeEntity feedLikeEntity = feedLikeRepository.findByFeedEntityIdAndUserId(feedId, userId)
                .orElse(null);

        if (feedLikeEntity == null) return null;
        return feedLikeMapper.entityToDomain(feedLikeEntity);
    }
}
