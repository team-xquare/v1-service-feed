package com.xquare.v1servicefeed.domain.attachment.domain.repository;

import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Adapter
public class AttachmentRepositoryAdapter {

    private final AttachmentRepository attachmentRepository;
}
