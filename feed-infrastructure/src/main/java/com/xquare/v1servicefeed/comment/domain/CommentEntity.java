package com.xquare.v1servicefeed.comment.domain;

import com.xquare.v1servicefeed.configuration.entity.BaseUUIDEntity;
import com.xquare.v1servicefeed.feed.domain.FeedEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_comment")
@Entity
public class CommentEntity extends BaseUUIDEntity {

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    private FeedEntity feed;

    @Column(columnDefinition = "BINARY(16)", nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public CommentEntity updateComment(String content, LocalDateTime createdAt) {
        this.content = content;
        this.createdAt = createdAt;
        return this;
    }
}
