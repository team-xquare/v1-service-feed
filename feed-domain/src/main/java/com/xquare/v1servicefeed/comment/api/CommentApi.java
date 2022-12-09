package com.xquare.v1servicefeed.comment.api;

import com.xquare.v1servicefeed.annotation.Api;
import com.xquare.v1servicefeed.comment.api.dto.request.CreateCommentDomainRequest;
import com.xquare.v1servicefeed.comment.api.dto.request.UpdateCommentDomainRequest;
import com.xquare.v1servicefeed.comment.api.dto.response.CommentDomainElement;

import java.util.UUID;
import java.util.List;

@Api
public interface CommentApi {
    void saveComment(CreateCommentDomainRequest request);

    void deleteCommentById(UUID commentId);

    void updateComment(UpdateCommentDomainRequest request);

    List<CommentDomainElement> queryAllCommentByFeedId(UUID feedId);
}
