package com.xquare.v1servicefeed.feed.domain.repository;

import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.FeedImage;
import com.xquare.v1servicefeed.feed.domain.FeedEntity;
import com.xquare.v1servicefeed.feed.domain.FeedImageEntity;
import com.xquare.v1servicefeed.feed.domain.mapper.FeedImageMapper;
import com.xquare.v1servicefeed.feed.domain.mapper.FeedMapper;
import com.xquare.v1servicefeed.feed.exception.FeedNotFoundException;
import com.xquare.v1servicefeed.feed.spi.FeedImageSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Adapter
public class FeedImageRepositoryAdapter implements FeedImageSpi {

    private final FeedImageRepository feedImageRepository;
    private final FeedImageMapper feedImageMapper;
    private final FeedRepository feedRepository;

    @Override
    @Transactional
    public void saveAllFeedImage(List<FeedImage> feedImage) {
        List<FeedImageEntity> feedImageList = feedImage.stream()
                .map(feedImageMapper::domainToEntity)
                .toList();
        feedImageRepository.saveAll(feedImageList);
    }

    @Override
    @Transactional
    public void deleteAllFeedImage(UUID feedId) {
        FeedEntity feedEntity = feedRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        feedImageRepository.deleteAllByFeedEntity(feedEntity);
    }

    @Override
    @Transactional
    public List<String> queryAllAttachmentsUrl(UUID feedId) {
        List<FeedImage> feedImageList = queryAllFeedImageByFeed(feedId);

        return feedImageList.stream()
                .map(FeedImage::getFilePath)
                .toList();
    }

    private List<FeedImage> queryAllFeedImageByFeed(UUID feedId) {
        FeedEntity feedEntity = feedRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        List<FeedImageEntity> feedImageList = feedImageRepository.findAllByFeedEntity(feedEntity);

        return feedImageList.stream()
                .map(feedImageEntity -> FeedImage.builder()
                        .number(feedImageEntity.getId().getNumber())
                        .feedId(feedImageEntity.getFeedEntity().getId())
                        .filePath(feedImageEntity.getFilePath())
                        .build())
                .toList();
    }
}
