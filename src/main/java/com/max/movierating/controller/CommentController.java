package com.max.movierating.controller;

import com.max.movierating.entity.Comment;
import com.max.movierating.service.impl.CommentService;
import com.max.movierating.service.impl.FilmServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/comments/")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final FilmServiceImpl filmService;

    @GetMapping("{id}")
    public ResponseEntity<Comment> findById(@PathVariable Long id) {
        return new ResponseEntity<>(commentService.findById(id), HttpStatus.OK);
    }

    @PostMapping("films/{id}")
    public ResponseEntity<Comment> save(@RequestBody Comment comment, @PathVariable Long id) {
        comment.setFilm(filmService.findById(id));
        return new ResponseEntity<>(commentService.save(comment), HttpStatus.CREATED);
    }
}