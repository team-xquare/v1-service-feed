package com.xquare.v1servicefeed.feedlike.domain;

import com.xquare.v1servicefeed.configuration.entity.BaseUUIDEntity;
import com.xquare.v1servicefeed.feed.domain.FeedEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

    @Column(nullable = false)
    private Integer likeCount;

    public void plusLikeCount() {
        this.likeCount += 1;
    }

    public void minusLikeCount() {
        this.likeCount -= 1;
    }
}
