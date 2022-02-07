package com.max.movierating.service.impl;

import com.max.movierating.entity.Film;
import com.max.movierating.entity.Genre;
import com.max.movierating.exception.ResourceDeleteException;
import com.max.movierating.exception.ResourceNotFoundException;
import com.max.movierating.repository.FilmRepository;
import com.max.movierating.service.DefaultService;
import com.max.movierating.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Film Service implementation that realize defaultService {@link DefaultService}
 * and filmService {@link FilmService} interfaces.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Service
@Slf4j
public class FilmServiceImpl implements DefaultService<Film, Long>, FilmService {

    private final FilmRepository filmRepository;
    private final GenreServiceImpl genreService;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository, GenreServiceImpl genreService) {
        this.filmRepository = filmRepository;
        this.genreService = genreService;
    }

    @Override
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public Film findById(Long id) {
        Optional<Film> filmOptional = filmRepository.findById(id);
        if (filmOptional.isEmpty()) {
            throw new ResourceNotFoundException("Film with id: " + id + " was not found");
        }

        return filmOptional.get();
    }

    @Override
    public Film save(Film film) {
        return filmRepository.save(film);
    }

    @Override
    public Film update(Film film, Long id) {
        Film existedFilm = findById(id);
        film.setId(id);
        film.setRating(existedFilm.getRating());

        return filmRepository.save(film);
    }

    @Override
    public Film deleteById(Long id) {
        Film film = findById(id);
        try {
            filmRepository.delete(film);
            log.info("Film with id: " + id + " was successfully deleted");
        } catch (DataIntegrityViolationException e) {
            log.error("Can't delete film");
            throw new ResourceDeleteException("Can't delete film");
        }

        return film;
    }

    @Override
    public List<Film> findAllByGenre(String genreName) {
        Genre genre = genreService.findByName(genreName);
        return filmRepository.findTop3ByGenresOrderByRatingDesc(genre);
    }

    @Override
    public Film findByName(String name) {
        return filmRepository.getByName(name);
    }

    @Override
    public Page<Film> getAllByPages(Pageable pageable) {
        return filmRepository.findAll(pageable);
    }

    @Override
    public Page<Film> findAllByName(Pageable pageable, String name) {
        return filmRepository.findAllByNameContaining(pageable, name);
    }

    public Page<Film> search(Pageable pageable, String name, Integer year, Double price) {
        return filmRepository.advancedSearch(pageable, name, year, price);
    }

}
