package com.xquare.v1servicefeed.feed.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.comment.spi.CommandCommentSpi;
import com.xquare.v1servicefeed.configuration.spi.SecuritySpi;
import com.xquare.v1servicefeed.feed.Category;
import com.xquare.v1servicefeed.feed.CategoryEnum;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.api.FeedApi;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainUpdateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedCategoryElement;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedCategoryListResponse;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedElement;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedList;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedListResponse;
import com.xquare.v1servicefeed.feed.api.dto.response.SaveFeedResponse;
import com.xquare.v1servicefeed.feed.spi.CommandFeedImageSpi;
import com.xquare.v1servicefeed.feed.spi.CommandFeedSpi;
import com.xquare.v1servicefeed.feed.spi.QueryCategorySpi;
import com.xquare.v1servicefeed.feed.spi.QueryFeedImageSpi;
import com.xquare.v1servicefeed.feed.spi.QueryFeedSpi;
import com.xquare.v1servicefeed.feedlike.spi.QueryFeedLikeSpi;
import com.xquare.v1servicefeed.user.User;
import com.xquare.v1servicefeed.user.exception.InvalidRoleException;
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
    private final QueryFeedLikeSpi queryFeedLikeSpi;
    private final QueryFeedImageSpi queryFeedImageSpi;
    private final QueryCategorySpi queryCategorySpi;

    @Override
    public SaveFeedResponse saveFeed(DomainCreateFeedRequest request) {
        Category category = queryCategorySpi.queryCategoryById(request.getCategoryId());
        List<String> userAuthorities = securitySpi.getUserAuthority();

        if (!securitySpi.isValidateUserAuthority(userAuthorities, category.getName())) {
            throw InvalidRoleException.EXCEPTION;
        }

        Feed feed = Feed.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .categoryId(request.getCategoryId())
                .userId(securitySpi.getCurrentUserId())
                .type(request.getType())
                .build();

        UUID feedId = commandFeedSpi.saveFeed(feed);

        return new SaveFeedResponse(feedId);
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
        UUID currentUserId = securitySpi.getCurrentUserId();

        List<FeedElement> feedList = queryFeedSpi.queryAllFeedByCategory(categoryId)
                .stream()
                .map(feed -> {
                    User user = hashMap.getOrDefault(feed.getUserId(), defaultUser);
                    boolean isLike = queryFeedLikeSpi.existsByUserIdAndFeedId(currentUserId, feed.getFeedId());
                    boolean isMine = user != null && feed.getUserId().equals(currentUserId);
                    List<String> attachmentsUrl = queryFeedImageSpi.queryAllAttachmentsUrl(feed.getFeedId());
                    return builderFeedElement(feed, user, attachmentsUrl, isMine, isLike);
                })
                .toList();

        return new FeedListResponse(feedList);
    }

    @Override
    public FeedCategoryListResponse getAllCategory() {
        List<FeedCategoryElement> categoryList = queryCategorySpi.queryAllCategory()
                .stream()
                .map(category -> FeedCategoryElement.builder()
                        .categoryId(category.getCategoryId())
                        .name(CategoryEnum.valueOf(category.getName()).getKoreaName())
                        .build())
                .toList();

        return new FeedCategoryListResponse(categoryList);
    }

    @Override
    public FeedListResponse getAllWriterFeed() {
        UUID currentUserId = securitySpi.getCurrentUserId();
        User user = feedUserSpi.queryUserByIds(List.of(currentUserId)).get(0);

        if (user == null) {
            return new FeedListResponse(List.of());
        }

        List<FeedElement> feedList = queryFeedSpi.queryAllFeedByUserId(user.getId())
                .stream()
                .map(feed -> {
                    boolean isLike = queryFeedLikeSpi.existsByUserIdAndFeedId(currentUserId, feed.getFeedId());
                    List<String> attachmentsUrl = queryFeedImageSpi.queryAllAttachmentsUrl(feed.getFeedId());
                    return builderFeedElement(feed, user, attachmentsUrl, true, isLike);
                })
                .toList();

        return new FeedListResponse(feedList);
    }

    private FeedElement builderFeedElement(
            FeedList feed, User user, List<String> attachmentsUrl, boolean isMine, boolean isLike
    ) {
        return FeedElement.builder()
                .feedId(feed.getFeedId())
                .content(feed.getContent())
                .createdAt(feed.getCreatedAt())
                .profile(user.getProfileFileName())
                .name(user.getName())
                .type(feed.getType())
                .likeCount(feed.getLikeCount())
                .commentCount(feed.getCommentCount())
                .isMine(isMine)
                .isLike(isLike)
                .attachmentsUrl(attachmentsUrl)
                .build();
    }
}
