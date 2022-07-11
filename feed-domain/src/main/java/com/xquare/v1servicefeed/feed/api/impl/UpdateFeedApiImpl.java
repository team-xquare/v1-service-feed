package com.xquare.v1servicefeed.feed.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.feed.api.UpdateFeedApi;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainUpdateFeedRequest;
import com.xquare.v1servicefeed.feed.spi.CommandFeedSpi;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@DomainService
public class UpdateFeedApiImpl implements UpdateFeedApi {

    private final CommandFeedSpi commandFeedSpi;

    @Override
    public void execute(UUID feedId, DomainUpdateFeedRequest request) {
        commandFeedSpi.updateFeed(feedId, request);
    }
}
