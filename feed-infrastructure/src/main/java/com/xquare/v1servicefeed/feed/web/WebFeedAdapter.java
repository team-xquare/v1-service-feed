package com.xquare.v1servicefeed.feed.web;

import com.xquare.v1servicefeed.feed.api.CreateFeedApi;
import com.xquare.v1servicefeed.feed.api.dto.request.DomainCreateFeedRequest;
<<<<<<< HEAD
=======
import com.xquare.v1servicefeed.feed.api.dto.response.FeedUuidResponse;
>>>>>>> 14-feed-create
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
<<<<<<< HEAD
    public void createFeed(WebCreateFeedRequest request) {

        DomainCreateFeedRequest domainRequest = DomainCreateFeedRequest.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .category(request.getCategory())
                .build();

        createFeedApi.execute(domainRequest);
=======
    public FeedUuidResponse createFeed(WebCreateFeedRequest request) {
        FeedUuidResponse response = createFeedApi.execute(
                DomainCreateFeedRequest.builder()
                        .title(request.getTitle())
                        .content(request.getContent())
                        .category(request.getCategory())
                        .build());

        return new FeedUuidResponse(response.getFeedUuid());
>>>>>>> 14-feed-create
    }
}
