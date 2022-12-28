package com.xquare.v1servicefeed.feed.domain.repository;

import com.xquare.v1servicefeed.feed.domain.FeedEntity;
import com.xquare.v1servicefeed.feed.domain.FeedImageEntity;
import com.xquare.v1servicefeed.feed.domain.FeedImageEntityId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedImageRepository extends CrudRepository<FeedImageEntity, FeedImageEntityId> {

    void deleteAllByFeedEntity(FeedEntity feed);
}
