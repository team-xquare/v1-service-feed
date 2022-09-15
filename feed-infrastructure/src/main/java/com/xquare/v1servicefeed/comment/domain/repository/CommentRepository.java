package com.xquare.v1servicefeed.comment.domain.repository;

import com.xquare.v1servicefeed.comment.Comment;
import com.xquare.v1servicefeed.comment.domain.CommentEntity;
import com.xquare.v1servicefeed.feed.Feed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, UUID> {
    void deleteAllByFeedId(UUID feedUuid);

}
