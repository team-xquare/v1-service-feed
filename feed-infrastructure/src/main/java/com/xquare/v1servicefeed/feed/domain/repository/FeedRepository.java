package com.xquare.v1servicefeed.feed.domain.repository;

import com.xquare.v1servicefeed.feed.domain.FeedEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FeedRepository extends CrudRepository<FeedEntity, UUID> {
}
