package com.xquare.v1servicefeed.declaration.spi;

import com.xquare.v1servicefeed.declaration.Declaration;

public interface CommandDeclarationSpi {
    void saveDeclaration(Declaration declaration);
}
