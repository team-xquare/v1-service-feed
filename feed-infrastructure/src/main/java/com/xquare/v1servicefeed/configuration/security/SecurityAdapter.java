package com.xquare.v1servicefeed.configuration.security;

import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import com.xquare.v1servicefeed.configuration.spi.SecuritySpi;
import com.xquare.v1servicefeed.feed.CategoryEnum;
import com.xquare.v1servicefeed.user.role.UserAuthority;
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
    public List<String> getUserAuthority() {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        return authorities
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }

    @Override
    public boolean isValidateUserAuthority(List<String> userAuthorities, String categoryName) {
        CategoryEnum category = CategoryEnum.valueOf(categoryName);

        for (UserAuthority authority : category.getAuthorities()) {
            if (userAuthorities.contains(authority.name())) {
                return true;
            }
        }
        return false;
    }
}
