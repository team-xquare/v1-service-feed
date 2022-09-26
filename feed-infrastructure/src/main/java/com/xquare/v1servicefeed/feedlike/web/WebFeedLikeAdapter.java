package com.xquare.v1servicefeed.feedlike.web;

import com.xquare.v1servicefeed.configuration.security.SecurityAdapter;
import com.xquare.v1servicefeed.feedlike.api.FeedLikeApi;
import com.xquare.v1servicefeed.feedlike.api.dto.DomainSaveFeedLikeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/likes")
@RestController
public class WebFeedLikeAdapter {
    private final SecurityAdapter securityAdapter;
    private final FeedLikeApi feedLikeApi;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void saveFeedLike(@Valid @RequestBody DomainSaveFeedLikeRequest request) {
        feedLikeApi.saveFeedLike(
                DomainSaveFeedLikeRequest.builder()
                        .feedId(request.getFeedId())
                        .userId(securityAdapter.getCurrentUserId())
                        .build()
        );
    }
}
