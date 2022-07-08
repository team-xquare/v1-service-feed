package com.xquare.v1servicefeed.comment.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.comment.Comment;
import com.xquare.v1servicefeed.comment.api.CreateCommentApi;
import com.xquare.v1servicefeed.comment.api.dto.request.DomainCreateCommnetRequest;
import com.xquare.v1servicefeed.comment.spi.CommandCommentSpi;
import com.xquare.v1servicefeed.comment.spi.CommentQueryFeedSpi;
import com.xquare.v1servicefeed.feed.Feed;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@DomainService
public class CreateCommentApiImpl implements CreateCommentApi {

    private final CommentQueryFeedSpi commentQueryFeedSpi;
    private final CommandCommentSpi commandCommentSpi;

    @Override
    public void execute(DomainCreateCommnetRequest request) {
        Feed feed = commentQueryFeedSpi.queryFeedById(request.getFeedUuid());

        commandCommentSpi.saveComment(
                Comment.builder()
                        .content(request.getContent())
                        .feedId(feed.getId())
                        .userId(request.getUserId())
                        .build()
        );
    }
}
