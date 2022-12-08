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
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.spi.QueryFeedSpi;
import com.xquare.v1servicefeed.user.User;
import com.xquare.v1servicefeed.user.spi.CommentUserSpi;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
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
    private final SecuritySpi securitySpi;

    @Override
    public void saveComment(CreateCommentDomainRequest request) {
        Feed feed = queryFeedSpi.queryFeedById(request.getFeedId());

        commandCommentSpi.saveComment(
                Comment.builder()
                        .content(request.getContent())
                        .feedId(feed.getId())
                        .userId(securitySpi.getCurrentUserId())
                        .createAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build()
        );
    }

    @Override
    public void deleteCommentById(UUID commentId) {
        commandCommentSpi.deleteCommentById(commentId);
    }

    @Override
    public void updateComment(UpdateCommentDomainRequest request) {
        commandCommentSpi.updateComment(request);
    }

    @Override
    public List<CommentDomainElement> queryComment(UUID feedId) {
        Feed feed = queryFeedSpi.queryFeedById(feedId);

        List<UUID> userIdList = queryCommentSpi.queryAllCommentUserIdByFeed(feed);
        Map<UUID, User> map = commentUserSpi.queryUserByIds(userIdList).stream()
                .collect(Collectors.toMap(User::getId, user -> user, (userId, user) -> user));

        return queryCommentSpi.queryAllCommentByFeed(feed)
                .stream()
                .map(comment -> {
                    User user = map.get(feed.getUserId());

                    return CommentDomainElement.builder()
                            .commentId(comment.getId())
                            .content(comment.getContent())
                            .name(user.getName())
                            .profile(user.getProfileFileName())
                            .updatedAt(comment.getUpdatedAt())
                            .build();
                })
                .toList();
    }
}
