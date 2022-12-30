package com.xquare.v1servicefeed.feed.web;

import com.xquare.v1servicefeed.feed.api.FeedImageApi;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedImageRequest;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainUpdateFeedImageRequest;
import com.xquare.v1servicefeed.feed.web.dto.request.WebCreateFeedImageRequest;
import com.xquare.v1servicefeed.feed.web.dto.request.WebUpdateFeedImageRequest;
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
    public void saveFeedImage(
            @PathVariable("feed-uuid") UUID feedId, @Valid @RequestBody WebCreateFeedImageRequest webRequest
    ) {
        DomainCreateFeedImageRequest domainRequest = DomainCreateFeedImageRequest.builder()
                .feedId(feedId)
                .attachmentsUrl(webRequest.getAttachmentsUrl())
                .build();
        feedImageApi.saveAllFeedImage(domainRequest);
    }

    @PatchMapping("/{feed-uuid}")
    public void updateFeedImage(
            @PathVariable("feed-uuid") UUID feedId, @Valid @RequestBody WebUpdateFeedImageRequest webRequest
    ) {
        DomainUpdateFeedImageRequest domainRequest = DomainUpdateFeedImageRequest.builder()
                .feedId(feedId)
                .attachmentsUrl(webRequest.getAttachmentsUrl())
                .build();

        feedImageApi.updateFeedImage(domainRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{feed-uuid}")
    public void deleteAllFeedImage(@PathVariable("feed-uuid") UUID feedId) {
        feedImageApi.deleteAllFeedImage(feedId);
    }
}
