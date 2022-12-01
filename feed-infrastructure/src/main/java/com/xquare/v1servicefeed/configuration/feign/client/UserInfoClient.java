package com.xquare.v1servicefeed.configuration.feign.client;

import com.xquare.v1servicefeed.configuration.feign.dto.response.UserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "userInfoClient", url = "https://stag-api.xquare.app/users/id/{userId}")
public interface UserInfoClient {

    @GetMapping
    UserInfoResponse getUserInfo(@PathVariable("userId") UUID userId);
}
