package com.xquare.v1servicefeed.comment.api;

import com.xquare.v1servicefeed.annotation.Api;
import com.xquare.v1servicefeed.comment.api.dto.request.DomainCreateCommentRequest;
import com.xquare.v1servicefeed.comment.api.dto.request.DomainUpdateCommentRequest;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainUpdateFeedRequest;

import java.util.UUID;

@Api
public interface CommentApi {
    void createComment(DomainCreateCommentRequest request);

    void deleteComment(UUID commentUuid);

    void updateComment(DomainUpdateCommentRequest request);
}
