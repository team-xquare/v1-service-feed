package com.xquare.v1servicefeed.feed.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.api.CreateFeedApi;
import com.xquare.v1servicefeed.feed.api.dto.DomainCreateFeedRequest;
import com.xquare.v1servicefeed.feed.spi.CommandFeedSpi;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@DomainService
public class CreateFeedApiImpl implements CreateFeedApi {

    private final CommandFeedSpi commandFeedSpi;

    @Override
    public UUID execute(DomainCreateFeedRequest request) {
        Feed feed = commandFeedSpi.saveFeed(
                Feed.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                        .category(request.getCategory())
                        .build());

        return feed.getId();
    }
}
