package com.xquare.v1servicefeed.configuration.security;

import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

@Adapter
public class SecurityAdapter {

    public UUID getCurrentUserId() {
        return UUID.fromString(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
