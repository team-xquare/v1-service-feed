package com.xquare.v1servicefeed.feign.client;

import com.xquare.v1servicefeed.feed.api.dto.response.AuthorityListByTypeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "AuthorityClient", url = "http://localhost:8081")
public interface AuthorityClient {

    @GetMapping("/authorities/access-management/type/{userId}")
    AuthorityListByTypeResponse getUserAuthoritiesByType(@PathVariable("userId") UUID userId, @RequestParam("type") String type);
}
