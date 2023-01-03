package com.xquare.v1servicefeed.configuration.security;

import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import com.xquare.v1servicefeed.configuration.spi.SecuritySpi;
import com.xquare.v1servicefeed.user.role.UserAuthorization;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Adapter
public class SecurityAdapter implements SecuritySpi {

    @Override
    public UUID getCurrentUserId() {
        return UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public boolean featureCallAuthorityComparison(List<UserAuthorization> featureAuthorityList) {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        for(UserAuthorization authority : featureAuthorityList) {
            String enumAuthority = authority.name();

            for(GrantedAuthority authorityList : authorities) {
                String grantedAuthority = authorityList.getAuthority();
                if (enumAuthority.equals(grantedAuthority)) {
                    return true;
                }
            }
        }
        return false;
    }
}
