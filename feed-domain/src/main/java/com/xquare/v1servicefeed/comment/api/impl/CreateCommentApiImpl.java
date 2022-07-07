package com.xquare.v1servicefeed.comment.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.comment.Comment;
import com.xquare.v1servicefeed.comment.api.CreateCommentApi;
import com.xquare.v1servicefeed.comment.api.dto.request.DomainCreateCommnetRequest;
import com.xquare.v1servicefeed.comment.spi.CommandCommentSpi;
import com.xquare.v1servicefeed.comment.spi.CommentQueryFeedApi;
import com.xquare.v1servicefeed.feed.Feed;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@DomainService
public class CreateCommentApiImpl implements CreateCommentApi {

    private final CommentQueryFeedApi commentQueryFeedApi;
    private final CommandCommentSpi commandCommentSpi;

    @Override
    public void execute(DomainCreateCommnetRequest request) {
        Feed feed = commentQueryFeedApi.queryFeedById(request.getFeedUuid());

        commandCommentSpi.saveComment(
                Comment.builder()
                        .content(request.getContent())
                        .feedId(feed.getId())
                        .userId(request.getUserId())
                        .build()
        );
    }
}
