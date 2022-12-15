package com.xquare.v1servicefeed.configuration.security;

import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import com.xquare.v1servicefeed.configuration.spi.SecuritySpi;
import com.xquare.v1servicefeed.user.exception.ForbiddenUserException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

@Adapter
public class SecurityAdapter implements SecuritySpi {

    @Override
    public UUID getCurrentUserId() {
        return UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
