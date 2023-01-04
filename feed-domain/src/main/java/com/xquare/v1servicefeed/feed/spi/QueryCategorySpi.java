package com.xquare.v1servicefeed.feed.spi;

import com.xquare.v1servicefeed.feed.Category;

import java.util.List;
import java.util.UUID;

public interface QueryCategorySpi {

    Category queryCategoryById(UUID categoryId);

    List<Category> queryAllCategory();
}
