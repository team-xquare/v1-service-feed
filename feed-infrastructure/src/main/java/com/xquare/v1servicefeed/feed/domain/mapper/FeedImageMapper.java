package com.xquare.v1servicefeed.feed.domain.mapper;

import com.xquare.v1servicefeed.feed.FeedImage;
import com.xquare.v1servicefeed.feed.domain.FeedEntity;
import com.xquare.v1servicefeed.feed.domain.FeedImageEntity;
import com.xquare.v1servicefeed.feed.domain.FeedImageEntityId;
import com.xquare.v1servicefeed.feed.domain.repository.FeedRepository;
import com.xquare.v1servicefeed.feed.exception.FeedNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FeedImageMapper {

    private final FeedRepository feedRepository;

    public FeedImageEntity domainToEntity(FeedImage feedImage) {
        FeedEntity feedEntity = feedRepository.findById(feedImage.getFeedId())
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        return FeedImageEntity.builder()
                .id(new FeedImageEntityId(feedImage.getFeedId(), feedImage.getNumber()))
                .filePath(feedImage.getFilePath())
                .feedEntity(feedEntity)
                .build();
    }

    public FeedImage entityToDomain(FeedImageEntity feedImageEntity) {
        return FeedImage.builder()
                .feedId(feedImageEntity.getId().getFeedId())
                .number(feedImageEntity.getId().getNumber())
                .filePath(feedImageEntity.getFilePath())
                .build();
    }
}
