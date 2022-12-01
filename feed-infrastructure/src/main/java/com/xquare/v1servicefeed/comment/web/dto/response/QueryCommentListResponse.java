package com.xquare.v1servicefeed.comment.web.dto.response;

import com.xquare.v1servicefeed.comment.api.dto.response.CommentDomainElement;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryCommentListResponse {

    private final List<CommentDomainElement> comments;
}
