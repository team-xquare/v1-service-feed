package com.xquare.v1servicefeed.feedlike.domain;

import com.xquare.v1servicefeed.configuration.entity.BaseUUIDEntity;
import com.xquare.v1servicefeed.feed.domain.FeedEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.UUID;

@Getter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_feed_like")
@Entity
public class FeedLikeEntity extends BaseUUIDEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private FeedEntity feed;

    @Column(columnDefinition = "BINARY(16)", nullable = false)
    private UUID userId;
}
