package com.xquare.v1servicefeed.feed.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.api.CreateFeedApi;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedRequest;
<<<<<<< HEAD
=======
import com.xquare.v1servicefeed.feed.api.dto.response.FeedUuidResponse;
>>>>>>> 14-feed-create
import com.xquare.v1servicefeed.feed.spi.CommandFeedSpi;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@DomainService
public class CreateFeedApiImpl implements CreateFeedApi {

    private final CommandFeedSpi commandFeedSpi;

    @Override
<<<<<<< HEAD
    public void execute(DomainCreateFeedRequest request) {
=======
    public FeedUuidResponse execute(DomainCreateFeedRequest request) {
>>>>>>> 14-feed-create
        Feed feed = Feed.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .category(request.getCategory())
                .build();

        commandFeedSpi.saveFeed(feed);
<<<<<<< HEAD
=======

        return new FeedUuidResponse(feed.getId());
>>>>>>> 14-feed-create
    }
}
