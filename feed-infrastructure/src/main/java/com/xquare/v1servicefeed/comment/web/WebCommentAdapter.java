package com.xquare.v1servicefeed.comment.web;

import com.xquare.v1servicefeed.comment.api.CommentApi;
import com.xquare.v1servicefeed.comment.api.dto.request.DomainCreateCommentRequest;
import com.xquare.v1servicefeed.comment.api.dto.request.UpdateCommentDomainRequest;
import com.xquare.v1servicefeed.comment.web.dto.request.CreateCommentWebRequest;
import com.xquare.v1servicefeed.comment.web.dto.request.UpdateCommentWebRequest;
import com.xquare.v1servicefeed.configuration.api.SecurityApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/comments")
@RestController
public class WebCommentAdapter {

    private final SecurityApi securityApi;
    private final CommentApi commentApi;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createComment(@Valid @RequestBody CreateCommentWebRequest request) {
        commentApi.createComment(
                DomainCreateCommentRequest.builder()
                        .userId(securityApi.getCurrentUserId())
                        .feedId(request.getFeedUuid())
                        .content(request.getContent())
                        .build()
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{comment-uuid}")
    public void deleteComment(@PathVariable("comment-uuid") UUID commentId) {
        commentApi.deleteComment(commentId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{comment-uuid}")
    public void updateComment(@PathVariable("comment-uuid") UUID commentId, @Valid @RequestBody UpdateCommentWebRequest request) {

        UpdateCommentDomainRequest domainRequest = UpdateCommentDomainRequest.builder()
                .commentId(commentId)
                .content(request.getContent())
                .build();

        commentApi.updateComment(domainRequest);
    }
}
