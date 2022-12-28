package com.xquare.v1servicefeed.feed.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @MapsId("feedId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "BINARY(16)", nullable = false)
    private FeedEntity feedEntity;

    @Column(length = 1024, nullable = false)
    private String filePath;
}
