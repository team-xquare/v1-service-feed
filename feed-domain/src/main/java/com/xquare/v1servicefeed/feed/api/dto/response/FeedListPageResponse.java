package com.xquare.v1servicefeed.feed.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FeedListPageResponse {

    private final List<FeedElement> feeds;
    private final long page;
    private final long totalPage;
}
