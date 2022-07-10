package com.xquare.v1servicefeed.feed.web;

import com.xquare.v1servicefeed.feed.api.CreateFeedApi;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedRequest;
import com.xquare.v1servicefeed.feed.api.dto.response.FeedUuidResponse;
import com.xquare.v1servicefeed.feed.web.dto.request.WebCreateFeedRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/feeds")
@RestController
public class WebFeedAdapter {

    private final CreateFeedApi createFeedApi;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public FeedUuidResponse createFeed(WebCreateFeedRequest request) {
        FeedUuidResponse response = createFeedApi.execute(
                DomainCreateFeedRequest.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                        .category(request.getCategory())
                        .build());

        return new FeedUuidResponse(response.getFeedUuid());
    }
}
