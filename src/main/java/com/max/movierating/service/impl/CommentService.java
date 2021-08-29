package com.max.movierating.service.impl;

import com.max.movierating.entity.Comment;
import com.max.movierating.exception.UserNotFoundException;
import com.max.movierating.repository.CommentRepository;
import com.max.movierating.service.DefaultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService implements DefaultService<Comment> {

    private final CommentRepository commentRepository;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Comment with id: " + id + " was not found"));
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment deleteById(Long id) {
        Comment comment = findById(id);
        commentRepository.delete(comment);
        return comment;
    }
}
