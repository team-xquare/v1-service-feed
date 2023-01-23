package com.xquare.v1servicefeed.feedlike.domain.mapper;

import com.xquare.v1servicefeed.feed.domain.FeedEntity;
import com.xquare.v1servicefeed.feed.domain.repository.FeedRepository;
import com.xquare.v1servicefeed.feed.exception.FeedNotFoundException;
import com.xquare.v1servicefeed.feedlike.FeedLike;
import com.xquare.v1servicefeed.feedlike.domain.FeedLikeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FeedLikeMapper {

    private final FeedRepository feedRepository;
    
    public FeedLike entityToDomain(FeedLikeEntity feedLikeEntity) {
        FeedEntity feedEntity = feedLikeEntity.getFeedEntity();

        return FeedLike.builder()
                .id(feedLikeEntity.getId())
                .feedId(feedEntity.getId())
                .userId(feedLikeEntity.getUserId())
                .build();
    }

    public FeedLikeEntity domainToEntity(FeedLike feedLike) {
        FeedEntity feedEntity = feedRepository.findById(feedLike.getFeedId())
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        return FeedLikeEntity.builder()
                .id(feedLike.getId())
                .feedEntity(feedEntity)
                .userId(feedLike.getUserId())
                .build();
    }
}
