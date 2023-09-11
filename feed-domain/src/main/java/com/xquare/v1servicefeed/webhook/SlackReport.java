package com.xquare.v1servicefeed.webhook;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SlackReport {

    private final String feedContent;

    private final String userName;

    private final String reportContent;
}
