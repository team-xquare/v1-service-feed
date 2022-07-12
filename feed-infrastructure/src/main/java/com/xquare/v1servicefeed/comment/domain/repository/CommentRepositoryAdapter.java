package com.xquare.v1servicefeed.comment.domain.repository;

import com.xquare.v1servicefeed.comment.Comment;
import com.xquare.v1servicefeed.comment.domain.mapper.CommentMapper;
import com.xquare.v1servicefeed.comment.spi.CommandCommentSpi;
import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Adapter
public class CommentRepositoryAdapter implements CommandCommentSpi {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public void saveComment(Comment comment) {
        commentRepository.save(
                commentMapper.domainToEntity(comment)
        );
    }
}
