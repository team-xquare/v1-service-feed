package com.xquare.v1servicefeed.configuration.api;

import com.xquare.v1servicefeed.annotation.Api;

import java.util.UUID;

@Api
public interface SecurityApi {
    UUID getCurrentUserId();
}
