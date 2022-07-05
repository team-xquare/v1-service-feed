package com.xquare.v1servicefeed.configuration.annotation;

import com.xquare.v1servicefeed.annotation.DomainService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(
        basePackages = {"com.xquare.v1servicefeed"},
        includeFilters = {
                @Filter(
                        type = FilterType.ANNOTATION,
                        value = DomainService.class
                )
        }
)
public class GlobalComponentScan {
}
