package com.xquare.v1servicefeed.feed.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.FeedImage;
import com.xquare.v1servicefeed.feed.api.FeedImageApi;
import com.xquare.v1servicefeed.feed.api.dto.request.CreateFeedImageRequest;
import com.xquare.v1servicefeed.feed.api.dto.request.UpdateFeedImageRequest;
import com.xquare.v1servicefeed.feed.spi.CommandFeedImageSpi;
import com.xquare.v1servicefeed.feed.spi.QueryFeedSpi;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@DomainService
public class FeedImageApiImpl implements FeedImageApi {

    private final CommandFeedImageSpi commandFeedImageSpi;
    private final QueryFeedSpi queryFeedSpi;

    @Override
    public void saveAllFeedImage(CreateFeedImageRequest request) {
        List<FeedImage> feedImageList = buildFeedImage(request.getFeedId(), request.getAttachmentsUrl());
        commandFeedImageSpi.saveAllFeedImage(feedImageList);
    }

    @Override
    public void deleteAllFeedImage(UUID feedId) {
        commandFeedImageSpi.deleteAllFeedImage(feedId);
    }

    @Override
    public void updateFeedImage(UpdateFeedImageRequest request) {
        Feed feed = queryFeedSpi.queryFeedById(request.getFeedId());

        commandFeedImageSpi.deleteAllFeedImage(feed.getId());
        List<FeedImage> feedImageList = buildFeedImage(request.getFeedId(), request.getAttachmentsUrl());
        commandFeedImageSpi.saveAllFeedImage(feedImageList);
    }

    private List<FeedImage> buildFeedImage(UUID feedId, List<String> getAttachmentsUrl) {
        List<FeedImage> feedImageList = new ArrayList<>();
        for (int i = 0; i < getAttachmentsUrl.size(); i++) {
            FeedImage feedImage = FeedImage.builder()
                    .number(i)
                    .feedId(feedId)
                    .filePath(getAttachmentsUrl.get(i))
                    .build();
            feedImageList.add(feedImage);
        }
        return feedImageList;
    }
}
