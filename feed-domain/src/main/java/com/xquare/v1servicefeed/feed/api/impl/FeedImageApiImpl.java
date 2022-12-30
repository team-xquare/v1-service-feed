package com.xquare.v1servicefeed.feed.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.FeedImage;
import com.xquare.v1servicefeed.feed.api.FeedImageApi;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedImageRequest;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainUpdateFeedImageRequest;
import com.xquare.v1servicefeed.feed.spi.CommandFeedImageSpi;
import com.xquare.v1servicefeed.feed.spi.QueryFeedImageSpi;
import com.xquare.v1servicefeed.feed.spi.QueryFeedSpi;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@DomainService
public class FeedImageApiImpl implements FeedImageApi {

    private final CommandFeedImageSpi commandFeedImageSpi;
    private final QueryFeedImageSpi queryFeedImageSpi;
    private final QueryFeedSpi queryFeedSpi;

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

    @Override
    public void updateFeedImage(DomainUpdateFeedImageRequest request) {
        Feed feed = queryFeedSpi.queryFeedById(request.getFeedId());
        List<FeedImage> feedImageList = queryFeedImageSpi.queryAllByFeed(feed);

        if (request.getAttachmentsUrl().size() > feedImageList.size()) {
            for (int i = 0; i < request.getAttachmentsUrl().size(); i++) {
                if (i < feedImageList.size() && !request.getAttachmentsUrl().get(i).equals(feedImageList.get(i).getFilePath())) {
                    saveFeedImage(i, request.getFeedId(), request.getAttachmentsUrl().get(i));
                } else {
                    saveFeedImage(i, request.getFeedId(), request.getAttachmentsUrl().get(i));
                }
            }
        } else if (feedImageList.size() > request.getAttachmentsUrl().size()) {
            for (int i = 0; i < feedImageList.size(); i++) {
                if (i < request.getAttachmentsUrl().size() && !request.getAttachmentsUrl().get(i).equals(feedImageList.get(i).getFilePath())) {
                    saveFeedImage(i, request.getFeedId(), request.getAttachmentsUrl().get(i));
                } else {
                    commandFeedImageSpi.deleteFeedImage(feedImageList.get(i));
                }
            }
        } else {
            for (int i = 0; i < request.getAttachmentsUrl().size(); i++) {
                if (!request.getAttachmentsUrl().get(i).equals(feedImageList.get(i).getFilePath())) {
                    saveFeedImage(i, request.getFeedId(), request.getAttachmentsUrl().get(i));
                }
            }
        }
    }

    private void saveFeedImage(Integer number, UUID feedId, String filePath) {
        FeedImage feedImage = FeedImage.builder()
                .number(number)
                .feedId(feedId)
                .filePath(filePath)
                .build();
        commandFeedImageSpi.saveFeedImage(feedImage);
    }
}
