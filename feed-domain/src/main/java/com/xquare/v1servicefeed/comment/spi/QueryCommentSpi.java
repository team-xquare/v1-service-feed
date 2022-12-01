package com.xquare.v1servicefeed.comment.spi;

import com.xquare.v1servicefeed.annotation.Spi;
import com.xquare.v1servicefeed.comment.api.dto.response.CommentDomainElement;

import java.util.List;
import java.util.UUID;

@Spi
public interface QueryCommentSpi {

    List<CommentDomainElement> findCommentByFeedId(UUID feedId);
}