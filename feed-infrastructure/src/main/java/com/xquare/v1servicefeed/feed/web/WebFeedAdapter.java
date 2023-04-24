package com.xquare.v1servicefeed.feed.web;

import com.xquare.v1servicefeed.report.api.ReportApi;
import com.xquare.v1servicefeed.report.api.dto.CreateReportDomainRequest;
import com.xquare.v1servicefeed.report.web.dto.request.CreateReportWebRequest;
import com.xquare.v1servicefeed.feed.api.FeedApi;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainUpdateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedCategoryListResponse;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedListResponse;
import com.xquare.v1servicefeed.feed.api.dto.response.SaveFeedResponse;
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
    private final ReportApi reportApi;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SaveFeedResponse saveFeed(@Valid @RequestBody WebCreateFeedRequest request) {

        DomainCreateFeedRequest domainRequest = DomainCreateFeedRequest.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .categoryId(request.getCategoryId())
                .type(request.getType())
                .build();

        return feedApi.saveFeed(domainRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{feed-uuid}")
    public void updateFeed(@PathVariable("feed-uuid") UUID feedId, @Valid @RequestBody WebUpdateFeedRequest request) {

        DomainUpdateFeedRequest domainRequest = DomainUpdateFeedRequest.builder()
                .feedId(feedId)
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        feedApi.updateFeed(domainRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{feed-uuid}")
    public void deleteFeed(@PathVariable("feed-uuid") UUID feedId) {
        feedApi.deleteFeedById(feedId);
    }

    @GetMapping
    public FeedListResponse getAllFeed(
            @RequestParam(value = "category", required = false) UUID categoryId,
            @RequestParam(defaultValue = "8") long limit,
            @RequestParam(defaultValue = "0") long page
    ) {
        return feedApi.getAllFeed(categoryId, limit, page);
    }

    @GetMapping("/category")
    public FeedCategoryListResponse getAllCategory() {
        return feedApi.getAllCategory();
    }

    @GetMapping("/writer")
    public FeedListResponse getAllWriterFeed() {
        return feedApi.getAllWriterFeed();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/report")
    public void saveReport(@Valid @RequestBody CreateReportWebRequest request) {

        CreateReportDomainRequest domainRequest = CreateReportDomainRequest.builder()
                .feedId(request.getFeedId())
                .reportUserId(request.getReportUserId())
                .content(request.getContent())
                .build();

        reportApi.saveReport(domainRequest);
    }
}
