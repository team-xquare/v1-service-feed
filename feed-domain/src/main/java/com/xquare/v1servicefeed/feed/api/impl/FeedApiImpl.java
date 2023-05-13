package com.xquare.v1servicefeed.feed.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.comment.spi.CommandCommentSpi;
import com.xquare.v1servicefeed.configuration.spi.SecuritySpi;
import com.xquare.v1servicefeed.feed.Category;
import com.xquare.v1servicefeed.feed.CategoryEnum;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.api.FeedApi;
import com.xquare.v1servicefeed.feed.api.dto.event.SaveFeedEvent;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainUpdateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.response.*;
import com.xquare.v1servicefeed.feed.spi.CommandFeedImageSpi;
import com.xquare.v1servicefeed.feed.spi.CommandFeedSpi;
import com.xquare.v1servicefeed.configuration.spi.EventPublisherSpi;
import com.xquare.v1servicefeed.feed.spi.QueryCategorySpi;
import com.xquare.v1servicefeed.feed.spi.QueryFeedImageSpi;
import com.xquare.v1servicefeed.feed.spi.QueryFeedSpi;
import com.xquare.v1servicefeed.feedlike.spi.CommandFeedLikeSpi;
import com.xquare.v1servicefeed.feedlike.spi.QueryFeedLikeSpi;
import com.xquare.v1servicefeed.user.User;
import com.xquare.v1servicefeed.user.exception.InvalidRoleException;
import com.xquare.v1servicefeed.user.role.UserAuthority;
import com.xquare.v1servicefeed.user.spi.FeedAuthoritySpi;
import com.xquare.v1servicefeed.user.spi.FeedUserSpi;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
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
    private final CommandFeedLikeSpi commandFeedLikeSpi;
    private final FeedAuthoritySpi feedAuthoritySpi;
    private final EventPublisherSpi eventPublisherSpi;

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
        eventPublisherSpi.publishEvent(new SaveFeedEvent(request.getType()));
        return new SaveFeedResponse(feedId);
    }

    @Override
    public void updateFeed(DomainUpdateFeedRequest request) {
        Feed feed = queryFeedSpi.queryFeedById(request.getFeedId());
        UUID currentUserId = securitySpi.getCurrentUserId();
        feedUserSpi.validateUserId(feed.getUserId(), currentUserId);

        commandFeedSpi.updateFeed(request);
    }

    @Override
    public void deleteFeedById(UUID feedId) {
        Feed feed = queryFeedSpi.queryFeedById(feedId);
        UUID currentUserId = securitySpi.getCurrentUserId();
        feedUserSpi.validateUserId(feed.getUserId(), currentUserId);
        commandCommentSpi.deleteAllCommentByFeedId(feedId);
        commandFeedImageSpi.deleteAllFeedImage(feedId);
        commandFeedLikeSpi.deleteFeedLikeByFeedId(feedId);
        commandFeedSpi.deleteFeed(feed);
    }

    @Override
    public FeedWeakElement getFeed(UUID feedId) {
        Feed feed = queryFeedSpi.queryFeedById(feedId);
        UserAuthority userAuthority = UserAuthority.valueOf(feed.getType());
        return FeedWeakElement.builder()
                .createdAt(feed.getCreatedAt())
                .attachmentsUrl(queryFeedImageSpi.queryAllAttachmentsUrl(feed.getId()))
                .content(feed.getContent())
                .feedId(feedId)
                .name(userAuthority.getName())
                .profile(userAuthority.getProfile())
                .title(feed.getTitle())
                .type(feed.getType())
                .build();
    }

    @Override
    public FeedListPageResponse getAllFeed(UUID categoryId, LocalDateTime dateTime, long limit) {
        List<UUID> userIdList = queryFeedSpi.queryAllFeedUserIdByCategory(categoryId);
        Map<UUID, User> hashMap = feedUserSpi.queryUserByIds(userIdList).stream()
                .collect(Collectors.toMap(User::getId, user -> user, (userId, user) -> user, HashMap::new));
        User defaultUser = User.builder().name("").profileFileName("").build();
        UUID currentUserId = securitySpi.getCurrentUserId();
        boolean isTest = isUserValidate();

        FeedPageList feedPageList = queryFeedSpi.queryAllFeedByCategory(categoryId, dateTime, limit);
        List<FeedElement> feedList = feedPageList.getFeedLists()
                .stream()
                .filter(feed -> !isTest || !feed.getType().equals(UserAuthority.UKN.name()))
                .map(feed -> {
                    UserAuthority userAuthority = UserAuthority.valueOf(feed.getType());
                    User user = hashMap.getOrDefault(feed.getUserId(), defaultUser);
                    boolean isLike = queryFeedLikeSpi.existsByUserIdAndFeedId(currentUserId, feed.getFeedId());
                    boolean isMine = user != null && feed.getUserId().equals(currentUserId);
                    List<String> attachmentsUrl = queryFeedImageSpi.queryAllAttachmentsUrl(feed.getFeedId());
                    return builderFeedElement(feed, user, userAuthority, attachmentsUrl, isMine, isLike);
                })
                .toList();

        return new FeedListPageResponse(feedList);
    }

    @Override
    public FeedCategoryListResponse getAllCategory() {
        boolean isTest = isUserValidate();
        UUID currentUserId = securitySpi.getCurrentUserId();
        List<FeedCategoryElement> categoryList = queryCategorySpi.queryAllCategory()
                .stream()
                .filter(category -> !isTest || !category.getName().equals(CategoryEnum.BAMBOO.getName()))
                .map(category -> {
                    CategoryEnum categoryEnum = CategoryEnum.valueOf(category.getName());

                    List<AuthorityElement> authoritiesByCategoryType =
                            feedAuthoritySpi.queryAuthorityByUserIdAndType(currentUserId, category.getName());

                    return FeedCategoryElement.builder()
                            .categoryId(category.getCategoryId())
                            .name(categoryEnum.getKoreaName())
                            .key(categoryEnum.getName())
                            .authorities(authoritiesByCategoryType)
                            .build();
                })
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
                    UserAuthority userAuthority = UserAuthority.valueOf(feed.getType());
                    boolean isLike = queryFeedLikeSpi.existsByUserIdAndFeedId(currentUserId, feed.getFeedId());
                    List<String> attachmentsUrl = queryFeedImageSpi.queryAllAttachmentsUrl(feed.getFeedId());
                    return builderFeedElement(feed, user, userAuthority, attachmentsUrl, true, isLike);
                })
                .toList();

        return new FeedListResponse(feedList);
    }

    private FeedElement builderFeedElement(
            FeedList feed, User user, UserAuthority userAuthority, List<String> attachmentsUrl, boolean isMine, boolean isLike
    ) {
        return FeedElement.builder()
                .feedId(feed.getFeedId())
                .title(feed.getTitle())
                .content(feed.getContent())
                .createdAt(feed.getCreatedAt())
                .profile(userAuthority.getProfile())
                .name(UserAuthority.UKN.name().equals(feed.getType()) ? "" : user.getName())
                .type(userAuthority.getName())
                .likeCount(feed.getLikeCount())
                .commentCount(feed.getCommentCount())
                .isMine(isMine)
                .isLike(isLike)
                .attachmentsUrl(attachmentsUrl)
                .build();
    }

    private boolean isUserValidate() {
        List<String> userAuthorities = securitySpi.getUserAuthority();
        return userAuthorities.contains(UserAuthority.TEST.name());
    }
}
