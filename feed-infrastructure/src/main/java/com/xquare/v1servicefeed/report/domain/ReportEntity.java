package com.xquare.v1servicefeed.report.domain;

import com.xquare.v1servicefeed.configuration.entity.BaseUUIDEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_report")
@Entity
public class ReportEntity extends BaseUUIDEntity {

    @Column(columnDefinition = "BINARY(16)", nullable = false)
    private UUID userId;

    @Column(columnDefinition = "BINARY(16)", nullable = false)
    private UUID feedId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
}
