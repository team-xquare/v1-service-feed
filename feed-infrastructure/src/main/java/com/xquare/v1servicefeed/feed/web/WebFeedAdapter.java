package com.xquare.v1servicefeed.feed.web;

import com.xquare.v1servicefeed.feed.api.CreateFeedApi;
import com.xquare.v1servicefeed.feed.api.UpdateFeedApi;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainUpdateFeedRequest;
import com.xquare.v1servicefeed.feed.web.dto.request.WebCreateFeedRequest;
import com.xquare.v1servicefeed.feed.web.dto.request.WebUpdateFeedRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/feeds")
@RestController
public class WebFeedAdapter {

    private final CreateFeedApi createFeedApi;
    private final UpdateFeedApi updateFeedApi;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createFeed(@Valid @RequestBody WebCreateFeedRequest request) {

        DomainCreateFeedRequest domainRequest = DomainCreateFeedRequest.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .category(request.getCategory())
                .build();

        createFeedApi.execute(domainRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{feed-uuid}")
    public void updateFeed(@PathVariable("feed-uuid") UUID feedId, @Valid @RequestBody WebUpdateFeedRequest request) {

        DomainUpdateFeedRequest domainRequest = DomainUpdateFeedRequest.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        updateFeedApi.execute(feedId, domainRequest);
    }
}
