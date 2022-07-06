package com.xquare.v1servicefeed.attachment.domain;

import com.xquare.v1servicefeed.feed.domain.FeedEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "tbl_feed_attachment")
@Entity
public class AttachmentEntity {

    @EmbeddedId
    private AttachmentEntityId attachmentEntityId;

    @MapsId("feedId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private FeedEntity feed;

    @Column(nullable = false)
    private String fileName;
}
