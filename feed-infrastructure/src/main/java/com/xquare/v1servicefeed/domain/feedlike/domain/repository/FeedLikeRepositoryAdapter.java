package com.xquare.v1servicefeed.domain.feedlike.domain.repository;

import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Adapter
public class FeedLikeRepositoryAdapter {

    private final FeedLikeRepository feedLikeRepository;
}
