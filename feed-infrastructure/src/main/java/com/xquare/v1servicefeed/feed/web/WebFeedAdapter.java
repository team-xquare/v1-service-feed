package com.xquare.v1servicefeed.feed.web;

import com.xquare.v1servicefeed.feed.api.FeedApi;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainUpdateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedCategoryListResponse;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedListPageResponse;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedListResponse;
import com.xquare.v1servicefeed.feed.api.dto.response.SaveFeedResponse;
import com.xquare.v1servicefeed.feed.web.dto.request.WebCreateFeedRequest;
import com.xquare.v1servicefeed.feed.web.dto.request.WebUpdateFeedRequest;
import com.xquare.v1servicefeed.report.api.ReportApi;
import com.xquare.v1servicefeed.report.api.dto.CreateReportDomainRequest;
import com.xquare.v1servicefeed.report.web.dto.request.CreateReportWebRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
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
    public FeedListPageResponse getAllFeed(
            @RequestParam(value = "category", required = false) UUID categoryId,
            @RequestParam(required = false) LocalDateTime dateTime,
            @RequestParam(defaultValue = "6") long limit
    ) {
        return feedApi.getAllFeed(categoryId, dateTime, limit);
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
