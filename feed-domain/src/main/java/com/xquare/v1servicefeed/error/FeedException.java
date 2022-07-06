package com.xquare.v1servicefeed.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class FeedException extends RuntimeException {

    private final ExceptionProperty exceptionProperty;
}
