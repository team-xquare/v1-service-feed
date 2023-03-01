package com.xquare.v1servicefeed.comment.domain.repository;

import com.xquare.v1servicefeed.comment.domain.CommentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, UUID> {

    void deleteById(UUID commentId);

    void deleteAllByFeedEntityId(UUID feedId);
}
