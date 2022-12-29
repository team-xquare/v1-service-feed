package com.xquare.v1servicefeed.feed.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.feed.FeedImage;
import com.xquare.v1servicefeed.feed.api.FeedImageApi;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedImageRequest;
import com.xquare.v1servicefeed.feed.spi.CommandFeedImageSpi;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@DomainService
public class FeedImageApiImpl implements FeedImageApi {

    private final CommandFeedImageSpi commandFeedImageSpi;

    @Override
    public void saveAllFeedImage(DomainCreateFeedImageRequest request) {
        List<FeedImage> feedImageList = new ArrayList<>();
        for (int i = 0; i < request.getAttachmentsUrl().size(); i++) {
            FeedImage feedImage = FeedImage.builder()
                    .feedId(request.getFeedId())
                    .filePath(request.getAttachmentsUrl().get(i))
                    .number(i)
                    .build();
            feedImageList.add(feedImage);
        }
        commandFeedImageSpi.saveAllFeedImage(feedImageList);
    }

    @Override
    public void deleteAllFeedImage(UUID feedId) {
        commandFeedImageSpi.deleteAllFeedImage(feedId);
    }
}
