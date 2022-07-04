package com.xquare.v1servicefeed.domain.feed.domain.repository;

import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Adapter
public class FeedRepositoryAdapter {

    private final FeedRepository feedRepository;
}
