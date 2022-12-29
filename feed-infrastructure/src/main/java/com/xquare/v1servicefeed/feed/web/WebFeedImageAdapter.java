package com.xquare.v1servicefeed.feed.web;

import com.xquare.v1servicefeed.feed.api.FeedImageApi;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedImageRequest;
import com.xquare.v1servicefeed.feed.web.dto.request.WebCreateFeedImageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/images")
@RestController
public class WebFeedImageAdapter {

    private final FeedImageApi feedImageApi;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{feed-uuid}")
    public void saveFeedImage(@PathVariable("feed-uuid") UUID feedId, @Valid @RequestBody WebCreateFeedImageRequest request) {
        DomainCreateFeedImageRequest domainCreateFeedImageRequest = DomainCreateFeedImageRequest.builder()
                .feedId(feedId)
                .attachmentsUrl(request.getAttachmentsUrl())
                .build();
        feedImageApi.saveAllFeedImage(domainCreateFeedImageRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{feed-uuid}")
    public void deleteAllFeedImage(@PathVariable("feed-uuid") UUID feedId) {
        feedImageApi.deleteAllFeedImage(feedId);
    }
}
