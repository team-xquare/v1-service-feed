package com.xquare.v1servicefeed.feed.domain.repository;

import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import com.xquare.v1servicefeed.feed.Category;
import com.xquare.v1servicefeed.feed.domain.mapper.CategoryMapper;
import com.xquare.v1servicefeed.feed.spi.CategorySpi;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Adapter
public class CategoryRepositoryAdapter implements CategorySpi {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<Category> queryAllCategory() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::entityToDomain)
                .toList();
    }
}
