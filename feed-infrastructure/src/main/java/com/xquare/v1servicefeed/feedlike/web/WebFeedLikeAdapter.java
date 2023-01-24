package com.xquare.v1servicefeed.feedlike.web;

import com.xquare.v1servicefeed.feedlike.api.FeedLikeApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/likes")
@RestController
public class WebFeedLikeAdapter {
    private final FeedLikeApi feedLikeApi;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{feed-uuid}")
    public void saveFeedLike(@PathVariable("feed-uuid") UUID feedId) {
        feedLikeApi.saveFeedLike(feedId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{feed-uuid}")
    public void cancelFeedLike(@PathVariable("feed-uuid") UUID feedId) {
        feedLikeApi.cancelFeedLike(feedId);
    }

}
