package com.xquare.v1servicefeed.comment.api;

import com.xquare.v1servicefeed.annotation.Api;
import com.xquare.v1servicefeed.comment.api.dto.request.CreateCommentDomainRequest;
import com.xquare.v1servicefeed.comment.api.dto.request.UpdateCommentDomainRequest;

import java.util.UUID;

@Api
public interface CommentApi {
    void createComment(CreateCommentDomainRequest request);

    void deleteComment(UUID commentUuid);

    void updateComment(UpdateCommentDomainRequest request);
}
