package com.xquare.v1servicefeed.feed.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FeedCategoryListResponse {

    List<FeedCategoryElement> categoryList;
}
