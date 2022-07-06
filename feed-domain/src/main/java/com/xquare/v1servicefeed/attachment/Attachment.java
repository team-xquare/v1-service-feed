package com.xquare.v1servicefeed.attachment;

import com.xquare.v1servicefeed.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@Aggregate
public class Attachment {

    private final UUID id;

    private final UUID feedId;

    private final String fileName;
}
