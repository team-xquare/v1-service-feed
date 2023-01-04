package com.xquare.v1servicefeed.feed.domain;

import com.xquare.v1servicefeed.configuration.entity.BaseUUIDEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_category")
@Entity
public class CategoryEntity extends BaseUUIDEntity {

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    private String name;

    @OneToOne(mappedBy = "categoryEntity")
    private FeedEntity feedEntity;
}
