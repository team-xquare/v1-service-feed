package com.xquare.v1servicefeed.comment.api;

import com.xquare.v1servicefeed.annotation.Api;
import com.xquare.v1servicefeed.comment.api.dto.request.DomainCreateCommnetRequest;

import java.util.UUID;

@Api
public interface CommentApi {
    void createComment(DomainCreateCommnetRequest request);



    void deleteComment(UUID commentUuid);
}
