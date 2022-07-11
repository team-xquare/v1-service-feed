package com.xquare.v1servicefeed.comment.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.comment.Comment;
import com.xquare.v1servicefeed.comment.api.DeleteCommentApi;
import com.xquare.v1servicefeed.comment.spi.CommandCommentSpi;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@DomainService
public class DeleteCommentApiImpl implements DeleteCommentApi {
    private final CommandCommentSpi commandCommentSpi;

    @Override
    public void execute(UUID commentId) {
        Comment comment = commandCommentSpi.findById(commentId);

        commandCommentSpi.deleteComment(comment);
    }

}
