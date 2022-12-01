package com.xquare.v1servicefeed.comment.domain.repository;

import com.xquare.v1servicefeed.comment.domain.CommentEntity;
import com.xquare.v1servicefeed.feed.domain.FeedEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, UUID> {

    void deleteById(UUID commentId);

    void deleteAllById(UUID feedId);

    List<CommentEntity> findAllByFeed(FeedEntity feed);
}
