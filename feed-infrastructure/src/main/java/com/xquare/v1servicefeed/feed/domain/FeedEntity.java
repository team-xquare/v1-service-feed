package com.xquare.v1servicefeed.feed.domain;

import com.xquare.v1servicefeed.configuration.entity.BaseUUIDEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity categoryEntity;

    @Column(nullable = false)
    private String authorityType;

    @Column(columnDefinition = "BIT(1) default 0", nullable = false)
    private boolean deleted;

    @OneToMany(mappedBy = "feedEntity")
    private Set<FeedImageEntity> feedImageEntities;

    public void updateFeedContent(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void delete(boolean deleted) {
        this.deleted = deleted;
    }
}
