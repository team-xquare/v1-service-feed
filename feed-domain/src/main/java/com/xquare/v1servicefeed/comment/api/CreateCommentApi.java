package com.xquare.v1servicefeed.comment.api;

import com.xquare.v1servicefeed.annotation.Api;
import com.xquare.v1servicefeed.comment.api.dto.request.DomainCreateCommnetRequest;

@Api
public interface CreateCommentApi {
    void execute(DomainCreateCommnetRequest request);
}
