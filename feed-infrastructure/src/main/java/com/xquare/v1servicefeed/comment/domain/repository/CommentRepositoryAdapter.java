package com.xquare.v1servicefeed.comment.domain.repository;

import com.xquare.v1servicefeed.comment.Comment;
import com.xquare.v1servicefeed.comment.domain.mapper.CommentMapper;
import com.xquare.v1servicefeed.comment.exception.CommentNotFoundException;
import com.xquare.v1servicefeed.comment.spi.CommandCommentSpi;
import com.xquare.v1servicefeed.configuration.annotation.Adapter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Adapter
public class CommentRepositoryAdapter implements CommandCommentSpi {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(
                commentMapper.domainToEntity(comment)
        );
    }

    @Override
    public Comment findById(UUID commentId) {
        return commentMapper.entityToDomain(
                commentRepository.findById(commentId)
                        .orElseThrow(() -> CommentNotFoundException.EXCEPTION)
        );
    }

    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete(
                commentMapper.domainToEntity(
                        comment
                )
        );
    }

}
