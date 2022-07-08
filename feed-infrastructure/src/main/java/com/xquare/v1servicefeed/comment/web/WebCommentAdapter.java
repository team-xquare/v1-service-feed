package com.xquare.v1servicefeed.comment.web;

import com.xquare.v1servicefeed.comment.api.CreateCommentApi;
import com.xquare.v1servicefeed.comment.api.dto.request.DomainCreateCommnetRequest;
import com.xquare.v1servicefeed.comment.web.dto.request.WebCreateCommentRequest;
import com.xquare.v1servicefeed.configuration.security.SecurityAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/comments")
@RestController
public class WebCommentAdapter {

    private final SecurityAdapter securityAdapter;

    private final CreateCommentApi createCommentApi;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createComment(WebCreateCommentRequest request) {
        createCommentApi.execute(
                DomainCreateCommnetRequest.builder()
                        .userId(securityAdapter.getCurrentUserId())
                        .feedUuid(request.getFeedUuid())
                        .content(request.getContent())
                        .build()
        );
    }

}
