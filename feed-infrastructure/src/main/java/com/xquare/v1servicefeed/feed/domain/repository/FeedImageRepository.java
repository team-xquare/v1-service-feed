package com.xquare.v1servicefeed.feed.domain.repository;

import com.xquare.v1servicefeed.feed.FeedImage;
import com.xquare.v1servicefeed.feed.domain.FeedEntity;
import com.xquare.v1servicefeed.feed.domain.FeedImageEntity;
import com.xquare.v1servicefeed.feed.domain.FeedImageEntityId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedImageRepository extends CrudRepository<FeedImageEntity, FeedImageEntityId> {

    void deleteAllByFeedEntity(FeedEntity feedEntity);

    List<FeedImageEntity> findAllByFeedEntity(FeedEntity feedEntity);
}
