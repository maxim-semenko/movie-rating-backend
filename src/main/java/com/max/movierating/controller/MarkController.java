package com.max.movierating.controller;

import com.max.movierating.constant.APIConstant;
import com.max.movierating.entity.Basket;
import com.max.movierating.entity.Film;
import com.max.movierating.service.impl.MarkServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for admin requests.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@RestController
@RequestMapping(value = APIConstant.MARKS_API)
@Slf4j
public class MarkController {

    private final MarkServiceImpl markService;

    @Autowired
    public MarkController(MarkServiceImpl markService) {
        this.markService = markService;
    }

    /**
     * Method that adds film to basket {@link Basket}.
     *
     * @param userId user's id
     * @param filmId film's id {@link com.max.movierating.entity.Film}
     * @return film with updated rating {@link Film}
     */
    @PostMapping("/user/{userId}/film/{filmId}")
    @PreAuthorize("hasRole('USER') and #userId == authentication.principal.id")
    public ResponseEntity<Film> addMark(@PathVariable Long userId,
                                        @PathVariable Long filmId,
                                        @RequestParam("value") Integer value) {
        return new ResponseEntity<>(markService.createMark(userId, filmId, value), HttpStatus.OK);
    }

    /**
     * Method that deletes film from basket {@link Basket}.
     *
     * @param userId user's id
     * @param filmId film's id {@link com.max.movierating.entity.Film}
     * @return film with updated rating {@link Film}
     */
    @DeleteMapping("/user/{userId}/film/{filmId}")
    @PreAuthorize("hasRole('USER') and #userId == authentication.principal.id")
    public ResponseEntity<Film> removeMark(@PathVariable Long userId, @PathVariable Long filmId) {
        return new ResponseEntity<>(markService.removeMarkByUserIdAndFilmId(userId, filmId), HttpStatus.OK);
    }
}
