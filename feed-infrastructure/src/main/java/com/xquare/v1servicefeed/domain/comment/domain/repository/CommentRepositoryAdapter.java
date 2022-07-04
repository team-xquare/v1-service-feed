package com.xquare.v1servicefeed.domain.comment.domain.repository;

import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Adapter
public class CommentRepositoryAdapter {

    private final CommentRepository commentRepository;
}
