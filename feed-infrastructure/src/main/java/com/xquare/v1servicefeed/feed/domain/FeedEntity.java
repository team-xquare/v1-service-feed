package com.xquare.v1servicefeed.feed.domain;

import com.xquare.v1servicefeed.configuration.entity.BaseUUIDEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_feed")
@Entity
public class FeedEntity extends BaseUUIDEntity {

    @Column(columnDefinition = "BINARY(16)", nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity categoryEntity;

    @Column(nullable = false)
    private String type;

    @OneToMany(mappedBy = "feedEntity")
    private Set<FeedImageEntity> feedImageEntities = new HashSet<>();

    public void updateFeed(String content) {
        this.content = content;
    }
}
