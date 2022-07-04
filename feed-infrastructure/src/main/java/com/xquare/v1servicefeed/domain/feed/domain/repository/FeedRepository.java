package com.xquare.v1servicefeed.domain.feed.domain.repository;

import com.xquare.v1servicefeed.domain.attachment.domain.AttachmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FeedRepository extends CrudRepository<AttachmentEntity, UUID> {
}
