package com.xquare.v1servicefeed.feed.api.dto.response;

import com.xquare.v1servicefeed.feed.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FeedCategoryResponse {

    List<Category> categories;
}
