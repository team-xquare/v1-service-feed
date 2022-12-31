package com.xquare.v1servicefeed.feed.web;

import com.xquare.v1servicefeed.feed.api.FeedImageApi;
import com.xquare.v1servicefeed.feed.api.dto.request.CreateFeedImageRequest;
import com.xquare.v1servicefeed.feed.api.dto.request.UpdateFeedImageRequest;
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
        CreateFeedImageRequest createFeedImageRequest = CreateFeedImageRequest.builder()
                .feedId(feedId)
                .attachmentsUrl(webRequest.getAttachmentsUrl())
                .build();
        feedImageApi.saveAllFeedImage(createFeedImageRequest);
    }

    @PatchMapping("/{feed-uuid}")
    public void updateFeedImage(
            @PathVariable("feed-uuid") UUID feedId, @Valid @RequestBody WebUpdateFeedImageRequest webRequest
    ) {
        UpdateFeedImageRequest updateFeedImageRequest = UpdateFeedImageRequest.builder()
                .feedId(feedId)
                .attachmentsUrl(webRequest.getAttachmentsUrl())
                .build();

        feedImageApi.updateFeedImage(updateFeedImageRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{feed-uuid}")
    public void deleteAllFeedImage(@PathVariable("feed-uuid") UUID feedId) {
        feedImageApi.deleteAllFeedImage(feedId);
    }
}
