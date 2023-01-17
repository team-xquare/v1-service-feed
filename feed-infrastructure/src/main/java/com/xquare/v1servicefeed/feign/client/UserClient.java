package com.xquare.v1servicefeed.feign.client;

import com.xquare.v1servicefeed.feign.client.dto.response.UserInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "UserClient", url = "${service.scheme}://${service.user.host}")
public interface UserClient {

    @GetMapping("/id")
    UserInfoResponse getUserInfo(@RequestParam("userId") List<UUID> userIds);
}
