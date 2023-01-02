package com.xquare.v1servicefeed.feed.web;

import com.xquare.v1servicefeed.feed.api.FeedApi;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainUpdateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedCategoryResponse;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedListResponse;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedSaveResponse;
import com.xquare.v1servicefeed.feed.web.dto.request.WebCreateFeedRequest;
import com.xquare.v1servicefeed.feed.web.dto.request.WebUpdateFeedRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class WebFeedAdapter {

    private final FeedApi feedApi;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FeedSaveResponse saveFeed(@Valid @RequestBody WebCreateFeedRequest request) {

        DomainCreateFeedRequest domainRequest = DomainCreateFeedRequest.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .category(request.getCategory())
                .build();

        return feedApi.saveFeed(domainRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{feed-uuid}")
    public void updateFeed(@PathVariable("feed-uuid") UUID feedId, @Valid @RequestBody WebUpdateFeedRequest request) {

        DomainUpdateFeedRequest domainRequest = DomainUpdateFeedRequest.builder()
                .feedId(feedId)
                .content(request.getContent())
                .attachmentsUrl(request.getAttachmentsUrl())
                .build();

        feedApi.updateFeed(domainRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{feed-uuid}")
    public void deleteFeed(@PathVariable("feed-uuid") UUID feedId) {
        feedApi.deleteFeedById(feedId);
    }

    @GetMapping
    public FeedListResponse getAllFeed(@RequestParam("category") UUID categoryId) {
        return feedApi.getAllFeed(categoryId);
    }

    @GetMapping("/category")
    public FeedCategoryResponse getAllCategory() {
        return feedApi.getAllCategory();
    }
}
