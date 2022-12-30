package com.xquare.v1servicefeed.feed.domain.repository;

import com.xquare.v1servicefeed.feed.domain.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, UUID> {
}
