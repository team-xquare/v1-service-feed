package com.xquare.v1servicefeed.feed.api.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class FeedCategoryElement {

    private final UUID categoryId;
    private final String name;
}
