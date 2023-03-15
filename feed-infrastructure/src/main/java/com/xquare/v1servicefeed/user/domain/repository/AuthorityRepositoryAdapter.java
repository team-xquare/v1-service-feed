package com.xquare.v1servicefeed.user.domain.repository;

import com.xquare.v1servicefeed.feed.api.dto.response.AuthorityElement;
import com.xquare.v1servicefeed.feign.client.AuthorityClient;
import com.xquare.v1servicefeed.user.spi.FeedAuthoritySpi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class AuthorityRepositoryAdapter implements FeedAuthoritySpi {

    private final AuthorityClient authorityClient;

    @Override
    public List<AuthorityElement> queryAuthorityByUserIdAndType(UUID userId, String type) {
        return authorityClient.getUserAuthoritiesByType(userId, type).getAuthorityList();
    }
}
