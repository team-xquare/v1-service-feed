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
import com.xquare.v1servicefeed.feed.spi.CommandFeedImageSpi;
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
    private final FeedUserSpi feedUserSpi;
    private final CommandCommentSpi commandCommentSpi;
    private final CommandFeedImageSpi commandFeedImageSpi;
    private final SecuritySpi securitySpi;
    private final QueryFeedSpi queryFeedSpi;

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
        Feed feed = queryFeedSpi.queryFeedById(request.getFeedId());
        UUID currentUserId = securitySpi.getCurrentUserId();
        feedUserSpi.validateUserId(feed.getUserId(), currentUserId);

        // TODO: 2021-08-02 이미지 로직 추가하기

        if (!feed.getContent().equals(request.getContent())) {
            commandFeedSpi.updateFeed(request);
        }
    }

    @Override
    public void deleteFeedById(UUID feedId) {
        Feed feed = queryFeedSpi.queryFeedById(feedId);
        UUID currentUserId = securitySpi.getCurrentUserId();
        feedUserSpi.validateUserId(feed.getUserId(), currentUserId);
        commandCommentSpi.deleteAllCommentByFeedId(feedId);
        commandFeedImageSpi.deleteAllFeedImage(feedId);
        commandFeedSpi.deleteFeed(feed);
    }

    @Override
    public FeedListResponse getAllFeed(String category) {
        List<UUID> userIdList = queryFeedSpi.queryAllFeedUserIdByCategory(category);
        Map<UUID, User> hashMap = feedUserSpi.queryUserByIds(userIdList).stream()
                .collect(Collectors.toMap(User::getId, user -> user, (userId, user) -> user, HashMap::new));
        User defaultUser = User.builder().name("").profileFileName("").build();
        hashMap.getOrDefault(UUID.randomUUID(), defaultUser);

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
                            .commentCount(feed.getCommentCount())
                            .build();
                })
                .toList();

        return new FeedListResponse(feedList);
    }
}
