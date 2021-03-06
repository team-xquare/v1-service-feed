package com.xquare.v1servicefeed.feedlike.domain.repository;

import com.xquare.v1servicefeed.feedlike.domain.FeedLikeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FeedLikeRepository extends CrudRepository<FeedLikeEntity, UUID> {
}
