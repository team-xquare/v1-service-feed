package com.xquare.v1servicefeed.feed.domain.mapper;

import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.domain.CategoryEntity;
import com.xquare.v1servicefeed.feed.domain.FeedEntity;
import com.xquare.v1servicefeed.feed.domain.repository.CategoryRepository;
import com.xquare.v1servicefeed.feed.exception.CategoryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FeedMapper {

    private final CategoryRepository categoryRepository;

    public Feed entityToDomain(FeedEntity feedEntity) {
        return Feed.builder()
                .id(feedEntity.getId())
                .userId(feedEntity.getUserId())
                .title(feedEntity.getTitle())
                .content(feedEntity.getContent())
                .createdAt(feedEntity.getCreatedAt())
                .categoryId(feedEntity.getCategoryEntity().getId())
                .type(feedEntity.getType())
                .build();
    }

    public FeedEntity domainToEntity(Feed feed) {
        CategoryEntity categoryEntity = categoryRepository.findById(feed.getCategoryId())
                .orElseThrow(() -> CategoryNotFoundException.EXCEPTION);

        return FeedEntity.builder()
                .id(feed.getId())
                .userId(feed.getUserId())
                .title(feed.getTitle())
                .content(feed.getContent())
                .createdAt(feed.getCreatedAt())
                .categoryEntity(categoryEntity)
                .type(feed.getType())
                .build();
    }
}
