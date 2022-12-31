package com.xquare.v1servicefeed.feed;

import com.xquare.v1servicefeed.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@Aggregate
public class Category {

    private final UUID categoryId;
    private final String name;
}
