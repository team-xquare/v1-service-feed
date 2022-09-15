package com.xquare.v1servicefeed.comment.web;

import com.xquare.v1servicefeed.comment.api.CommentApi;
import com.xquare.v1servicefeed.comment.api.dto.request.DomainCreateCommentRequest;
import com.xquare.v1servicefeed.comment.web.dto.request.WebCreateCommentRequest;
import com.xquare.v1servicefeed.configuration.security.SecurityAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/comments")
@RestController
public class WebCommentAdapter {

    private final SecurityAdapter securityAdapter;

    private final CommentApi commentApi;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createComment(@Valid @RequestBody WebCreateCommentRequest request) {
        commentApi.createComment(
                DomainCreateCommentRequest.builder()
                        .userId(securityAdapter.getCurrentUserId())
                        .feedUuid(request.getFeedUuid())
                        .content(request.getContent())
                        .build()
        );

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{comment-uuid}")
    public void deleteComment(@PathVariable("comment-uuid") UUID commentUuid) {
        commentApi.deleteComment(commentUuid);
    }

}
