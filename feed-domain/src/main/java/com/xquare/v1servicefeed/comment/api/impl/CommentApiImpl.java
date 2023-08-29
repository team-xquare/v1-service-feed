package com.xquare.v1servicefeed.comment.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.comment.Comment;
import com.xquare.v1servicefeed.comment.api.CommentApi;
import com.xquare.v1servicefeed.comment.api.dto.request.CreateCommentDomainRequest;
import com.xquare.v1servicefeed.comment.api.dto.request.UpdateCommentDomainRequest;
import com.xquare.v1servicefeed.comment.api.dto.response.CommentDomainElement;
import com.xquare.v1servicefeed.comment.spi.CommandCommentSpi;
import com.xquare.v1servicefeed.comment.spi.QueryCommentSpi;
import com.xquare.v1servicefeed.configuration.spi.SecuritySpi;
import com.xquare.v1servicefeed.feed.CategoryEnum;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.spi.CategorySpi;
import com.xquare.v1servicefeed.feed.spi.QueryFeedSpi;
import com.xquare.v1servicefeed.notification.extension.CommentNotificationUtilImpl;
import com.xquare.v1servicefeed.user.User;
import com.xquare.v1servicefeed.user.role.UserAuthority;
import com.xquare.v1servicefeed.user.role.UserRole;
import com.xquare.v1servicefeed.user.spi.CommentUserSpi;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@DomainService
public class CommentApiImpl implements CommentApi {

    private final QueryFeedSpi queryFeedSpi;
    private final CommentUserSpi commentUserSpi;
    private final CommandCommentSpi commandCommentSpi;
    private final QueryCommentSpi queryCommentSpi;
    private final CommentNotificationUtilImpl commentNotificationUtil;
    private final CategorySpi categorySpi;
    private final SecuritySpi securitySpi;
    private final String FEED_NOTICE_COMMENT = "FEED_NOTICE_COMMENT";
    private final String FEED_BAMBOO_COMMENT = "FEED_BAMBOO_COMMENT";

    @Override
    public void saveComment(CreateCommentDomainRequest request) {
        Feed feed = queryFeedSpi.queryFeedById(request.getFeedId());
        UUID userId = securitySpi.getCurrentUserId();

        commandCommentSpi.saveComment(
                Comment.builder()
                        .content(request.getContent())
                        .feedId(feed.getId())
                        .userId(userId)
                        .createAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .isDeleted(false)
                        .build()
        );

        if(!feed.getUserId().equals(userId)) {
            sendNotification(feed);
        }
    }

    private void sendNotification(Feed feed) {
        if(CategoryEnum.NOTICE.getName().equals(categorySpi.queryCategoryById(feed.getCategoryId()).getName())) {
            commentNotificationUtil.sendNotification(feed, FEED_NOTICE_COMMENT);
        } else {
            commentNotificationUtil.sendNotification(feed, FEED_BAMBOO_COMMENT);
        }
    }

    @Override
    public void deleteCommentById(UUID commentId) {
        Comment comment = queryCommentSpi.queryCommentById(commentId);
        UUID currentUserId = securitySpi.getCurrentUserId();
        commentUserSpi.validateUserId(comment.getUserId(), currentUserId);
        commandCommentSpi.deleteCommentById(commentId);
    }

    @Override
    public void updateComment(UpdateCommentDomainRequest request) {
        Comment comment = queryCommentSpi.queryCommentById(request.getCommentId());
        UUID currentUserId = securitySpi.getCurrentUserId();
        commentUserSpi.validateUserId(comment.getUserId(), currentUserId);
        commandCommentSpi.updateComment(request);
    }

    @Override
    public List<CommentDomainElement> queryAllCommentByFeedId(UUID feedId) {
        Feed feed = queryFeedSpi.queryFeedById(feedId);
        UUID currentUserId = securitySpi.getCurrentUserId();

        Map<UUID, User> map = commentUserSpi.queryAllUserByRole(UserRole.STU.name()).stream()
                .collect(Collectors.toMap(User::getId, user -> user, (userId, user) -> user, HashMap::new));
        User defaultUser = User.builder().name("").profileFileName("").build();

        return queryCommentSpi.queryAllCommentByFeed(feed)
                .stream()
                .map(comment -> {
                    User user = map.getOrDefault(comment.getUserId(), defaultUser);
                    boolean isMine = comment.getUserId().equals(currentUserId);

                    return CommentDomainElement.builder()
                            .commentId(comment.getId())
                            .content(comment.getContent())
                            .name(UserAuthority.UKN.name().equals(feed.getAuthorityType()) ? "" : user.getName())
                            .profile(UserAuthority.UKN.name().equals(feed.getAuthorityType()) ? "" : user.getProfileFileName())
                            .updatedAt(comment.getUpdatedAt())
                            .isMine(isMine)
                            .build();
                })
                .toList();
    }
}
