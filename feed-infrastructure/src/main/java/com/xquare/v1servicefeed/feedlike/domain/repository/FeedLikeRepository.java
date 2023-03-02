package com.xquare.v1servicefeed.feedlike.domain.repository;

import com.xquare.v1servicefeed.feed.domain.FeedEntity;
import com.xquare.v1servicefeed.feedlike.domain.FeedLikeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FeedLikeRepository extends CrudRepository<FeedLikeEntity, UUID> {

    boolean existsByUserIdAndFeedEntityId(UUID userId, UUID feedEntityId);

    Optional<FeedLikeEntity> findByFeedEntityIdAndUserId(UUID feedEntityId, UUID userId);

    void deleteAllByFeedEntityId(UUID feedEntityId);
}
