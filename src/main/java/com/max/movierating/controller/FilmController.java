package com.max.movierating.controller;

import com.max.movierating.entity.Film;
import com.max.movierating.service.impl.FilmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * The Film REST controller that takes request films API.
 */
@RestController
@RequestMapping("api/v1/films")
public class FilmController {

    /**
     * Film service that execute any operations with film entity.
     */
    private final FilmServiceImpl filmService;

    @Autowired
    public FilmController(FilmServiceImpl filmService) {
        this.filmService = filmService;
    }

    /**
     * Method that returns all films by pages and size.
     *
     * @param pageable {@link Pageable} contain page and size
     * @return all films
     */
    @GetMapping("")
    public ResponseEntity<Page<Film>> findAll(Pageable pageable) {
        return new ResponseEntity<>(filmService.getAllByPages(pageable), HttpStatus.OK);
    }

    /**
     * Method that returns the film by given id;
     *
     * @param id {@link Long} the film's id
     * @return the film
     */
    @GetMapping("/{id}")
    public ResponseEntity<Film> findById(@PathVariable Long id) {
        return new ResponseEntity<>(filmService.findById(id), HttpStatus.OK);
    }

    /**
     * Method that returns the film by given name.
     *
     * @param name the film's name
     * @return the film
     */
    @GetMapping("/filter")
    public ResponseEntity<Page<Film>> filterSearch(Pageable pageable,
                                                   @RequestParam(value = "name", required = false) String name,
                                                   @RequestParam(value = "year", required = false) Integer year,
                                                   @RequestParam(value = "price", required = false) Double price) {
        return new ResponseEntity<>(filmService.search(pageable, name, year, price), HttpStatus.OK);
    }

    /**
     * Method that save new film by given {@link RequestBody}.
     *
     * @param film {@link Film} request body that contain params
     * @return new film
     */
    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Film> create(@Valid @RequestBody Film film) {
        return new ResponseEntity<>(filmService.save(film), HttpStatus.CREATED);
    }

    /**
     * Method that save new film by given {@link RequestBody}.
     *
     * @param film {@link Film} request body that contain params
     * @return new film
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Film> update(@PathVariable Long id, @Valid @RequestBody Film film) {
        return new ResponseEntity<>(filmService.update(film, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Film> delete(@PathVariable Long id) {
        return new ResponseEntity<>(filmService.deleteById(id), HttpStatus.OK);
    }

}
