package com.xquare.v1servicefeed.configuration.security;

import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import com.xquare.v1servicefeed.configuration.spi.SecuritySpi;
import com.xquare.v1servicefeed.user.exception.InvalidRoleException;
import com.xquare.v1servicefeed.user.role.UserAuthorization;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.UUID;

@Adapter
public class SecurityAdapter implements SecuritySpi {

    private final Authentication getSecurityContext = SecurityContextHolder.getContext().getAuthentication();
    @Override
    public UUID getCurrentUserId() {
        return UUID.fromString(getSecurityContext.getName());
    }

    @Override
    public void featureCallAuthorityComparison(List<UserAuthorization> featureAuthority) {
        for (UserAuthorization authority : featureAuthority) {
            if (!getSecurityContext.getAuthorities().toString().equals(authority.toString())) {
                throw InvalidRoleException.EXCEPTION;
            }
        }
    }
}
