package com.xquare.v1servicefeed.feign.client.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserInfoResponse {

    private List<UserInfoElement> users;
}
