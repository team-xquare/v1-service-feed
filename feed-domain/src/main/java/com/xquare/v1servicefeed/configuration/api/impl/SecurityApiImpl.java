package com.xquare.v1servicefeed.configuration.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.configuration.api.SecurityApi;
import com.xquare.v1servicefeed.configuration.spi.SecuritySpi;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@DomainService
public class SecurityApiImpl implements SecurityApi {

    private final SecuritySpi securitySpi;

    @Override
    public UUID getCurrentUserId() {
        return securitySpi.getCurrentUserId();
    }
}
