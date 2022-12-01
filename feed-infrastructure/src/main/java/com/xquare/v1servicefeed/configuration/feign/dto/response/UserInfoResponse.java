package com.xquare.v1servicefeed.configuration.feign.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoResponse {
    private final String name;
    private final String profileFileName;
}
