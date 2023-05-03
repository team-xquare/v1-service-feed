package com.xquare.v1servicefeed.feedlike.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.configuration.spi.SecuritySpi;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.spi.QueryFeedSpi;
import com.xquare.v1servicefeed.feedlike.FeedLike;
import com.xquare.v1servicefeed.feedlike.api.FeedLikeApi;
import com.xquare.v1servicefeed.feedlike.exception.FeedLikeExistsException;
import com.xquare.v1servicefeed.feedlike.exception.FeedLikeNotFoundException;
import com.xquare.v1servicefeed.feedlike.spi.CommandFeedLikeSpi;
import com.xquare.v1servicefeed.feedlike.spi.QueryFeedLikeSpi;
import com.xquare.v1servicefeed.notification.NotificationSpi;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@DomainService
public class FeedLikeApiImpl implements FeedLikeApi {
    private final CommandFeedLikeSpi commandFeedLikeSpi;
    private final QueryFeedSpi queryFeedSpi;
    private final QueryFeedLikeSpi queryFeedLikeSpi;
    private final SecuritySpi securitySpi;
    private final NotificationSpi notificationSpi;

    @Override
    public void saveFeedLike(UUID feedId) {
        Feed feed = queryFeedSpi.queryFeedById(feedId);
        UUID userId = securitySpi.getCurrentUserId();

        if (queryFeedLikeSpi.existsByUserIdAndFeedId(userId, feed.getId())) {
            throw FeedLikeExistsException.EXCEPTION;
        }

        commandFeedLikeSpi.saveFeedLike(
                FeedLike.builder()
                        .feedId(feed.getId())
                        .userId(securitySpi.getCurrentUserId())
                        .build()
        );

        notificationSpi.sendNotification(
                feed.getUserId(),
                "FEED_LIKE",
                "좋아요가 달렸습니다.",
                feed.getId().toString()
        );
    }

    @Override
    public void cancelFeedLike(UUID feedId) {
        UUID userId = securitySpi.getCurrentUserId();
        FeedLike feedLike = queryFeedLikeSpi.queryFeedLikeByFeedIdAndUserId(feedId, userId);

        if (feedLike == null) {
            throw FeedLikeNotFoundException.EXCEPTION;
        }
        commandFeedLikeSpi.cancelFeedLike(feedLike);
    }
}
