package com.xquare.v1servicefeed.configuration.spi;

import com.xquare.v1servicefeed.annotation.Spi;
import com.xquare.v1servicefeed.feed.Category;

import java.util.UUID;

@Spi
public interface SecuritySpi {
    UUID getCurrentUserId();

    boolean isValidateUserAuthority(String categoryName);
}
