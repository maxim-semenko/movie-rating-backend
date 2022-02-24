package com.max.movierating.controller;

import com.max.movierating.constant.APIConstant;
import com.max.movierating.dto.entity.RequestMarkDTO;
import com.max.movierating.entity.Film;
import com.max.movierating.entity.Mark;
import com.max.movierating.service.impl.MarkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * REST controller for marks requests.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@RestController
@RequestMapping(value = APIConstant.MARKS_API)
public class MarkController {

    private final MarkServiceImpl markService;

    @Autowired
    public MarkController(MarkServiceImpl markService) {
        this.markService = markService;
    }

    /**
     * Method that adds mark to film {@link Film}.
     *
     * @return created mark {@link Mark}
     */
    @PostMapping("")
    @PreAuthorize("hasRole('USER') and #request.userId == authentication.principal.id")
    public ResponseEntity<Mark> addMark(@Valid @RequestBody RequestMarkDTO request) {
        return new ResponseEntity<>(markService.createMark(request), HttpStatus.OK);
    }

    /**
     * Method that deletes mark from film {@link Film}.
     *
     * @return deleted mark {@link Mark}
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') and #userId == authentication.principal.id")
    public ResponseEntity<Mark> removeMark(@PathVariable Long id, @RequestParam("userId") Long userId) {
        return new ResponseEntity<>(markService.removeMarkById(id), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/film/{filmId}")
    public ResponseEntity<Mark> getMark(@PathVariable Long userId, @PathVariable Long filmId) {
        return new ResponseEntity<>(markService.findMarkByUserIdAndFilmId(userId, filmId), HttpStatus.OK);
    }

}
