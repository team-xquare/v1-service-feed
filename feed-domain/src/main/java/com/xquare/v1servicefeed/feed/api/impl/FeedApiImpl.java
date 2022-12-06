package com.xquare.v1servicefeed.feed.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.comment.spi.CommandCommentSpi;
import com.xquare.v1servicefeed.configuration.spi.SecuritySpi;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.api.FeedApi;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainUpdateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedListElement;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedListResponse;
import com.xquare.v1servicefeed.feed.spi.CommandFeedSpi;
import com.xquare.v1servicefeed.feed.spi.QueryFeedSpi;
import com.xquare.v1servicefeed.user.User;
import com.xquare.v1servicefeed.user.spi.FeedUserSpi;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@DomainService
public class FeedApiImpl implements FeedApi {

    private final CommandFeedSpi commandFeedSpi;
    private final CommandCommentSpi commandCommentSpi;
    private final SecuritySpi securitySpi;
    private final QueryFeedSpi queryFeedSpi;
    private final FeedUserSpi feedUserSpi;

    @Override
    public void saveFeed(DomainCreateFeedRequest request) {
        Feed feed = Feed.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .category(request.getCategory())
                .userId(securitySpi.getCurrentUserId())
                .build();

        commandFeedSpi.saveFeed(feed);
    }

    @Override
    public void updateFeed(DomainUpdateFeedRequest request) {
        commandFeedSpi.updateFeed(request);
    }

    @Override
    public void deleteFeedById(UUID feedId) {
        Feed feed = queryFeedSpi.queryFeedById(feedId);

        commandCommentSpi.deleteAllCommentByFeedId(feedId);
        commandFeedSpi.deleteFeed(feed);

    }

    @Override
    public FeedListResponse getAllFeed(String category) {
        List<UUID> userIdList = queryFeedSpi.queryAllFeedUserIdByCategory(category);
        Map<UUID, User> hashMap = feedUserSpi.queryUserByIds(userIdList).stream()
                .collect(Collectors.toMap(User::getId, user -> user, (a, b) -> b, HashMap::new));

        List<FeedListElement> feedList = queryFeedSpi.queryAllFeedByCategory(category)
                .stream()
                .map(feed -> {
                    User user = hashMap.get(feed.getUserId());

                    return FeedListElement.builder()
                            .feedId(feed.getId())
                            .content(feed.getContent())
                            .createdAt(feed.getCreatedAt())
                            .profile(user.getProfileFileName())
                            .name(user.getName())
                            .likeCount(feed.getLikeCount())
                            .build();
                })
                .toList();

        return new FeedListResponse(feedList);
    }
}
