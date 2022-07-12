package com.xquare.v1servicefeed.feed.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.feed.api.UpdateFeedApi;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainUpdateFeedRequest;
import com.xquare.v1servicefeed.feed.spi.CommandFeedSpi;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@DomainService
public class UpdateFeedApiImpl implements UpdateFeedApi {

    private final CommandFeedSpi commandFeedSpi;

    @Override
    public void execute(DomainUpdateFeedRequest request) {
        commandFeedSpi.updateFeed(request);
    }
}
