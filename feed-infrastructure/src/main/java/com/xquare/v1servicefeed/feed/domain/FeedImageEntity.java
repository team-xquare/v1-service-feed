package com.xquare.v1servicefeed.feed.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_feed_image")
@Entity
public class FeedImageEntity implements Serializable {

    @EmbeddedId
    private FeedImageEntityId id;

    @Column(length = 1024, nullable = false)
    private String filePath;

    @MapsId("id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private FeedEntity feed;
}
