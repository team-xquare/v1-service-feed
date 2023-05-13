package com.xquare.v1servicefeed.configuration.spi;

public interface EventPublisherSpi {

    void publishEvent(Object event);
}
