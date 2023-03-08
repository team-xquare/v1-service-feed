package com.xquare.v1servicefeed.declaration.domain.mapper;

import com.xquare.v1servicefeed.declaration.Declaration;
import com.xquare.v1servicefeed.declaration.domain.DeclarationEntity;
import com.xquare.v1servicefeed.feed.domain.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public abstract class DeclarationMapper {

    public Declaration entityToDomain(DeclarationEntity declarationEntity) {

        return Declaration.builder()
                .id(declarationEntity.getId())
                .userId(declarationEntity.getUserId())
                .feedId(declarationEntity.getFeedId())
                .content(declarationEntity.getContent())
                .build();
    }

    public DeclarationEntity domainToEntity(Declaration declaration) {

        return DeclarationEntity.builder()
                .id(declaration.getId())
                .userId(declaration.getUserId())
                .feedId(declaration.getFeedId())
                .content(declaration.getContent())
                .build();
    }
}
