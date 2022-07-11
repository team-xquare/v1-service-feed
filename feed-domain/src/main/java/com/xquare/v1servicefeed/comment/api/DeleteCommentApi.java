package com.xquare.v1servicefeed.comment.api;

import com.xquare.v1servicefeed.annotation.Api;

import java.util.UUID;

@Api
public interface DeleteCommentApi {
    void execute(UUID commentUuid);
}
