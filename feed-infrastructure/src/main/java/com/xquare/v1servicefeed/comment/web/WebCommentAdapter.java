package com.xquare.v1servicefeed.comment.web;

import com.xquare.v1servicefeed.comment.api.CommentApi;
import com.xquare.v1servicefeed.comment.api.dto.request.CreateCommentDomainRequest;
import com.xquare.v1servicefeed.comment.api.dto.request.UpdateCommentDomainRequest;
import com.xquare.v1servicefeed.comment.web.dto.request.CreateCommentWebRequest;
import com.xquare.v1servicefeed.comment.web.dto.request.UpdateCommentWebRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/comments")
@RestController
public class WebCommentAdapter {

    private final CommentApi commentApi;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void saveComment(@Valid @RequestBody CreateCommentWebRequest request) {
        commentApi.saveComment(
                CreateCommentDomainRequest.builder()
                        .feedId(request.getFeedUuid())
                        .content(request.getContent())
                        .build()
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{comment-uuid}")
    public void deleteComment(@PathVariable("comment-uuid") @NotNull UUID commentId) {
        commentApi.deleteCommentById(commentId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{comment-uuid}")
    public void updateComment(@PathVariable("comment-uuid") @NotNull UUID commentId, @Valid @RequestBody UpdateCommentWebRequest request) {

        UpdateCommentDomainRequest domainRequest = UpdateCommentDomainRequest.builder()
                .commentId(commentId)
                .content(request.getContent())
                .build();

        commentApi.updateComment(domainRequest);
    }
}
