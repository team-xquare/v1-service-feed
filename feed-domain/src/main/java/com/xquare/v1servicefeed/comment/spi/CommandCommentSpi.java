package com.xquare.v1servicefeed.comment.spi;

import com.xquare.v1servicefeed.annotation.Spi;
import com.xquare.v1servicefeed.comment.Comment;
import com.xquare.v1servicefeed.comment.api.dto.request.UpdateCommentDomainRequest;
import com.xquare.v1servicefeed.feed.Feed;

import java.util.UUID;

@Spi
public interface CommandCommentSpi {
    void saveComment(Comment comment);

    void deleteCommentById(UUID commentId);

    void deleteAllComment(Feed feed);

    void updateComment(UpdateCommentDomainRequest request);

}
