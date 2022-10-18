package com.xquare.v1servicefeed.comment.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.comment.Comment;
import com.xquare.v1servicefeed.comment.api.CommentApi;
import com.xquare.v1servicefeed.comment.api.dto.request.CreateCommentDomainRequest;
import com.xquare.v1servicefeed.comment.api.dto.request.UpdateCommentDomainRequest;
import com.xquare.v1servicefeed.comment.spi.CommandCommentSpi;
import com.xquare.v1servicefeed.configuration.spi.SecuritySpi;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.spi.QueryFeedSpi;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@DomainService
public class CommentApiImpl implements CommentApi {

    private final QueryFeedSpi queryFeedSpi;
    private final CommandCommentSpi commandCommentSpi;
    private final SecuritySpi securitySpi;

    @Override
    public void saveComment(CreateCommentDomainRequest request) {
        Feed feed = queryFeedSpi.queryFeedById(request.getFeedId());

        commandCommentSpi.saveComment(
                Comment.builder()
                        .content(request.getContent())
                        .feedId(feed.getId())
                        .userId(securitySpi.getCurrentUserId())
                        .build()
        );
    }

    @Override
    public void deleteComment(UUID commentId) {
        commandCommentSpi.deleteCommentById(commentId);
    }

    @Override
    public void updateComment(UpdateCommentDomainRequest request) {
        commandCommentSpi.updateComment(request);
    }
}
