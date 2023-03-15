package com.xquare.v1servicefeed.user.spi;

import com.xquare.v1servicefeed.feed.api.dto.response.AuthorityElement;

import java.util.List;
import java.util.UUID;

public interface FeedAuthoritySpi {

    List<AuthorityElement> queryAuthorityByUserIdAndType(UUID userId, String type);
}
