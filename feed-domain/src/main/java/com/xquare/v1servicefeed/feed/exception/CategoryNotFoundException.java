package com.xquare.v1servicefeed.feed.exception;

import com.xquare.v1servicefeed.error.FeedException;
import com.xquare.v1servicefeed.feed.error.CategoryErrorCode;

public class CategoryNotFoundException extends FeedException {

    public static final CategoryNotFoundException EXCEPTION = new CategoryNotFoundException();

    private CategoryNotFoundException() {
        super(CategoryErrorCode.CATEGORY_NOT_FOUND);
    }
}
