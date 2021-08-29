package com.max.movierating.controller;

import com.max.movierating.entity.Film;
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

import java.util.List;

/**
 * The Film REST controller that takes request films API.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/films")
@RequiredArgsConstructor
public class FilmController {

    /**
     * Film service that execute any operations with film entity.
     */
    private final FilmServiceImpl filmService;

    /**
     * Method that returns all films.
     *
     * @return all films
     */
    @GetMapping("")
    public ResponseEntity<List<Film>> getAll() {
        return new ResponseEntity<>(filmService.findAll(), HttpStatus.OK);
    }

    /**
     * Method that returns the film by given id;
     *
     * @param id the film's id
     * @return the film
     */
    @GetMapping("/{id}")
    public ResponseEntity<Film> get(@PathVariable Long id) {
        return new ResponseEntity<>(filmService.findById(id), HttpStatus.OK);
    }

    /**
     * Method that returns the film by given name.
     *
     * @param name the film's name
     * @return the film
     */
//    @GetMapping("/{name}")
//    public ResponseEntity<Film> getByName(@PathVariable String name) {
//        return new ResponseEntity<>(filmService.getByName(name), HttpStatus.OK);
//    }

    /**
     * Method that save new film by given {@link RequestBody}.
     *
     * @param film request body that contain params
     * @return new film
     */
    @PostMapping("")
    public ResponseEntity<Film> save(@RequestBody Film film) {
        return new ResponseEntity<>(filmService.save(film), HttpStatus.CREATED);
    }
}