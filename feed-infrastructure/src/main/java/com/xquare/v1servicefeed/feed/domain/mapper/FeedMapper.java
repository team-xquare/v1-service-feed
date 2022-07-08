package com.xquare.v1servicefeed.feed.domain.mapper;

import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.domain.FeedEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FeedMapper {

    public Feed entityToDomain(FeedEntity feedEntity) {
        return Feed.builder()
                .id(feedEntity.getId())
                .userId(feedEntity.getUserId())
                .title(feedEntity.getTitle())
                .content(feedEntity.getContent())
                .createdAt(feedEntity.getCreatedAt())
                .likeCount(feedEntity.getLikeCount())
                .category(feedEntity.getCategory())
                .build();
    }

    public FeedEntity domainToEntity(Feed feed) {
        return FeedEntity.builder()
                .id(feed.getId())
                .userId(feed.getUserId())
                .title(feed.getTitle())
                .content(feed.getContent())
                .createdAt(feed.getCreatedAt())
                .likeCount(feed.getLikeCount())
                .category(feed.getCategory())
                .build();
    }
}
