package com.xquare.v1servicefeed.feign.client.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserInfoResponse {

    private final List<UserInfoElement> users;
}
