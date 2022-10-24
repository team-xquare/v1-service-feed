package com.xquare.v1servicefeed.feedlike.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.configuration.spi.SecuritySpi;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.spi.QueryFeedSpi;
import com.xquare.v1servicefeed.feedlike.FeedLike;
import com.xquare.v1servicefeed.feedlike.api.FeedLikeApi;
import com.xquare.v1servicefeed.feedlike.api.dto.SaveFeedLikeDomainRequest;
import com.xquare.v1servicefeed.feedlike.exception.InvalidUserException;
import com.xquare.v1servicefeed.feedlike.spi.CommandFeedLikeSpi;
import com.xquare.v1servicefeed.feedlike.spi.QueryFeedLikeSpi;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@DomainService
public class FeedLikeApiImpl implements FeedLikeApi {
    private final CommandFeedLikeSpi commandFeedLikeSpi;
    private final QueryFeedSpi queryFeedSpi;
    private final QueryFeedLikeSpi queryFeedLikeSpi;
    private final SecuritySpi securitySpi;

    @Override
    public void saveFeedLike(SaveFeedLikeDomainRequest request) {
        Feed feed = queryFeedSpi.queryFeedById(request.getFeedId());

        commandFeedLikeSpi.saveFeedLike(
                FeedLike.builder()
                        .feedId(feed.getId())
                        .userId(securitySpi.getCurrentUserId())
                        .build(),
                feed
        );
    }

    @Override
    public void deleteFeedLike(UUID feedLikeId) {
        Feed feed = queryFeedSpi.queryFeedById(feedLikeId);
        FeedLike feedLike = queryFeedLikeSpi.queryFeedLikeByFeed(feed);
        UUID userId = securitySpi.getCurrentUserId();

        if (commandFeedLikeSpi.existsUser(userId)) {
            throw InvalidUserException.EXCEPTION;
        }

        commandFeedLikeSpi.deleteFeedLike(feed, feedLike);
    }
}
