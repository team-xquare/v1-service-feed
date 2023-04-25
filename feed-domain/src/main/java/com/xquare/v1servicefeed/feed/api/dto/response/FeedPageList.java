package com.xquare.v1servicefeed.feed.api.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class FeedPageList {
    private final List<FeedList> feedLists;
    private final long totalPage;
}
