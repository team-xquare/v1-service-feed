package com.xquare.v1servicefeed.declaration.api.impl;

import com.xquare.v1servicefeed.annotation.DomainService;
import com.xquare.v1servicefeed.configuration.spi.SecuritySpi;
import com.xquare.v1servicefeed.declaration.Declaration;
import com.xquare.v1servicefeed.declaration.api.DeclarationApi;
import com.xquare.v1servicefeed.declaration.api.dto.CreateDeclarationDomainRequest;
import com.xquare.v1servicefeed.declaration.spi.CommandDeclarationSpi;
import com.xquare.v1servicefeed.feed.Feed;
import com.xquare.v1servicefeed.feed.spi.QueryFeedSpi;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@DomainService
public class DeclarationApiImpl implements DeclarationApi {

    private final QueryFeedSpi queryFeedSpi;
    private final CommandDeclarationSpi commandDeclarationSpi;
    private final SecuritySpi securitySpi;

    @Override
    public void saveDeclaration(CreateDeclarationDomainRequest request) {
        Feed feed = queryFeedSpi.queryFeedById(request.getFeedId());

        commandDeclarationSpi.saveDeclaration(
                Declaration.builder()
                        .userId(securitySpi.getCurrentUserId())
                        .feedId(feed.getId())
                        .content(request.getContent())
                        .build()
        );
    }
}
