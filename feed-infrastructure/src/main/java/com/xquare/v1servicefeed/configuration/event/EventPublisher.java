package com.xquare.v1servicefeed.configuration.event;

import com.xquare.v1servicefeed.configuration.spi.EventPublisherSpi;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EventPublisher implements EventPublisherSpi {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishEvent(Object event) {
        applicationEventPublisher.publishEvent(event);
    }

}
