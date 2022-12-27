package com.xquare.v1servicefeed.feed.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
public class FeedImageEntityId implements Serializable {

    @Column(columnDefinition = "BINARY(16)", nullable = false)
    private UUID feedId;

    @Column(nullable = false)
    private Integer order;
}
