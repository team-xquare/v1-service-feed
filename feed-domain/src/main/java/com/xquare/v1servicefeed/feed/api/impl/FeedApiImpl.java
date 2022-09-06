package com.xquare.v1servicefeed.feed.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.comment.api.dto.request.DomainCreateCommnetRequest;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.api.FeedApi;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainUpdateFeedRequest;
import com.xquare.v1servicefeed.feed.spi.CommandFeedSpi;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@DomainService
public class FeedApiImpl implements FeedApi {

    private final CommandFeedSpi commandFeedSpi;

    @Override
    public void createFeed(DomainCreateFeedRequest request) {
        Feed feed = Feed.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .category(request.getCategory())
                .build();

        commandFeedSpi.saveFeed(feed);
    }

    @Override
    public void updateFeed(DomainUpdateFeedRequest request) {
        commandFeedSpi.updateFeed(request);
    }
}

