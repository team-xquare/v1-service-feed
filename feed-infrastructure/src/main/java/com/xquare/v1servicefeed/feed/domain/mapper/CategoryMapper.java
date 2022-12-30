package com.xquare.v1servicefeed.feed.domain.mapper;

import com.xquare.v1servicefeed.feed.Category;
import com.xquare.v1servicefeed.feed.domain.CategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CategoryMapper {

    public CategoryEntity domainToEntity(Category category) {
        return CategoryEntity.builder()
                .id(category.getCategoryId())
                .name(category.getName())
                .build();
    }

    public Category entityToDomain(CategoryEntity categoryEntity) {
        return Category.builder()
                .categoryId(categoryEntity.getId())
                .name(categoryEntity.getName())
                .build();
    }
}
