package com.xquare.v1servicefeed.configuration.spi;

import com.xquare.v1servicefeed.annotation.Spi;
import com.xquare.v1servicefeed.user.role.UserAuthorization;

import java.util.List;
import java.util.UUID;

@Spi
public interface SecuritySpi {
    UUID getCurrentUserId();

    void featureCallAuthorityComparison(List<UserAuthorization> featureAuthority);
}
