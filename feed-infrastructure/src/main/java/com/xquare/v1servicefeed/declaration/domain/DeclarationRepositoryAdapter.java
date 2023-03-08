package com.xquare.v1servicefeed.declaration.domain;

import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import com.xquare.v1servicefeed.declaration.Declaration;
import com.xquare.v1servicefeed.declaration.domain.mapper.DeclarationMapper;
import com.xquare.v1servicefeed.declaration.domain.repository.DeclarationRepository;
import com.xquare.v1servicefeed.declaration.spi.DeclarationSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Adapter
public class DeclarationRepositoryAdapter implements DeclarationSpi {

    private final DeclarationMapper declarationMapper;
    private final DeclarationRepository declarationRepository;

    @Override
    @Transactional
    public void saveDeclaration(Declaration declaration) {
        declarationRepository.save(
                declarationMapper.domainToEntity(declaration)
        );
    }
}
