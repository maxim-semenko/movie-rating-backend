package com.max.movierating.controller;

import com.max.movierating.entity.Film;
import com.max.movierating.service.impl.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Film controller.
 */
@RestController
@RequestMapping("api/v1/films")
public class FilmController {

    private final FilmService filmService;

    /**
     * Instantiates a new Film controller.
     *
     * @param filmService the film service
     */
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @GetMapping("")
    public ResponseEntity<List<Film>> getAll() {
        return new ResponseEntity<>(filmService.findAll(), HttpStatus.OK);
    }

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Film> get(@PathVariable Long id) {
        return new ResponseEntity<>(filmService.findById(id), HttpStatus.OK);
    }

    /**
     * Save response entity.
     *
     * @param film the film
     * @return the response entity
     */
    @PostMapping("")
    public ResponseEntity<Film> save(@RequestBody Film film) {
        return new ResponseEntity<>(filmService.save(film), HttpStatus.CREATED);
    }
}
