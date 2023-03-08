package com.xquare.v1servicefeed.declaration.api;

import com.xquare.v1servicefeed.annotation.Api;
import com.xquare.v1servicefeed.declaration.api.dto.CreateDeclarationDomainRequest;

@Api
public interface DeclarationApi {

    void saveDeclaration(CreateDeclarationDomainRequest request);
}
