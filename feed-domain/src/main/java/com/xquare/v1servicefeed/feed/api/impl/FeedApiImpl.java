package com.xquare.v1servicefeed.feed.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.comment.spi.CommandCommentSpi;
import com.xquare.v1servicefeed.configuration.spi.SecuritySpi;
import com.xquare.v1servicefeed.feed.Category;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.api.FeedApi;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainUpdateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedCategoryResponse;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedListElement;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedListResponse;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedSaveResponse;
import com.xquare.v1servicefeed.feed.spi.*;
import com.xquare.v1servicefeed.feedlike.FeedLike;
import com.xquare.v1servicefeed.feedlike.spi.QueryFeedLikeSpi;
import com.xquare.v1servicefeed.user.User;
import com.xquare.v1servicefeed.user.role.UserAuthorization;
import com.xquare.v1servicefeed.user.spi.FeedUserSpi;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

import static com.xquare.v1servicefeed.user.role.UserAuthorization.*;

@RequiredArgsConstructor
@DomainService
public class FeedApiImpl implements FeedApi {

    private final CommandFeedSpi commandFeedSpi;
    private final FeedUserSpi feedUserSpi;
    private final CommandCommentSpi commandCommentSpi;
    private final CommandFeedImageSpi commandFeedImageSpi;
    private final SecuritySpi securitySpi;
    private final QueryFeedSpi queryFeedSpi;
    private final QueryFeedLikeSpi queryFeedLikeSpi;
    private final QueryFeedImageSpi queryFeedImageSpi;
    private final QueryCategorySpi queryCategorySpi;

    @Override
    public FeedSaveResponse saveFeed(DomainCreateFeedRequest request) {
        List<UserAuthorization> authorizations = new ArrayList<>(Set.of(STD, STC, STA, DOD, DOS, CLL));
        securitySpi.featureCallAuthorityComparison(authorizations);
        Feed feed = Feed.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .categoryId(request.getCategory())
                .userId(securitySpi.getCurrentUserId())
                .build();

        commandFeedSpi.saveFeed(feed);

        return new FeedSaveResponse(feed.getId());
    }

    @Override
    public void updateFeed(DomainUpdateFeedRequest request) {
        Feed feed = queryFeedSpi.queryFeedById(request.getFeedId());
        UUID currentUserId = securitySpi.getCurrentUserId();
        feedUserSpi.validateUserId(feed.getUserId(), currentUserId);

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
    public FeedListResponse getAllFeed(UUID categoryId) {
        List<UUID> userIdList = queryFeedSpi.queryAllFeedUserIdByCategory(categoryId);
        Map<UUID, User> hashMap = feedUserSpi.queryUserByIds(userIdList).stream()
                .collect(Collectors.toMap(User::getId, user -> user, (userId, user) -> user, HashMap::new));
        User defaultUser = User.builder().name("").profileFileName("").build();
        hashMap.getOrDefault(UUID.randomUUID(), defaultUser);
        UUID currentUserId = securitySpi.getCurrentUserId();

        List<FeedListElement> feedList = queryFeedSpi.queryAllFeedByCategory(categoryId)
                .stream()
                .map(feed -> {
                    User user = hashMap.get(feed.getUserId());
                    FeedLike feedLike = queryFeedLikeSpi.queryFeedLikeByFeed(feed);
                    Boolean isLike = feedLike.getUserId().equals(currentUserId);
                    Boolean isMine = feed.getUserId().equals(currentUserId);
                    List<String> attachmentsUrl = queryFeedImageSpi.queryAllAttachmentsUrl(feed);

                    return FeedListElement.builder()
                            .feedId(feed.getId())
                            .content(feed.getContent())
                            .createdAt(feed.getCreatedAt())
                            .profile(user.getProfileFileName())
                            .name(user.getName())
                            .likeCount(feed.getLikeCount())
                            .commentCount(feed.getCommentCount())
                            .isMine(isMine)
                            .isLike(isLike)
                            .attachmentsUrl(attachmentsUrl)
                            .build();
                })
                .toList();

        return new FeedListResponse(feedList);
    }

    @Override
    public FeedCategoryResponse getAllCategory() {
        List<String> categoryList = queryCategorySpi.queryAllCategory()
                .stream()
                .map(Category::getName)
                .toList();

        return new FeedCategoryResponse(categoryList);
    }
}
