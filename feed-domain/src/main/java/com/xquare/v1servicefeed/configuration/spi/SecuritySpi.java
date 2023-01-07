package com.xquare.v1servicefeed.configuration.spi;

import com.xquare.v1servicefeed.annotation.Spi;
import com.xquare.v1servicefeed.user.role.UserAuthority;

import java.util.List;
import java.util.UUID;

@Spi
public interface SecuritySpi {
    UUID getCurrentUserId();

    boolean isValidateUserAuthority(List<UserAuthority> userAuthorities, String categoryName);

    List<UserAuthority> getUserAuthority();
}
