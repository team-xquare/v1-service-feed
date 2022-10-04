package com.xquare.v1servicefeed.feedlike.web;

import com.xquare.v1servicefeed.configuration.api.SecurityApi;
import com.xquare.v1servicefeed.feedlike.api.FeedLikeApi;
import com.xquare.v1servicefeed.feedlike.api.dto.SaveFeedLikeDomainRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/likes")
@RestController
public class WebFeedLikeAdapter {
    private final FeedLikeApi feedLikeApi;
    private final SecurityApi securityApi;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{feed-uuid}")
    public void saveFeedLike(@PathVariable("feed-uuid") UUID feedId) {
        feedLikeApi.saveFeedLike(
                SaveFeedLikeDomainRequest.builder()
                        .feedId(feedId)
                        .userId(securityApi.getCurrentUserId())
                        .build()
        );
    }
}
