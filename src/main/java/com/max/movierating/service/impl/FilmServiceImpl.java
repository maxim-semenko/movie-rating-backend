package com.max.movierating.service.impl;

import com.max.movierating.entity.Film;
import com.max.movierating.entity.Genre;
import com.max.movierating.exception.ResourceNotFoundException;
import com.max.movierating.repository.FilmRepository;
import com.max.movierating.service.DefaultService;
import com.max.movierating.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Film Service implementation that realize
 * {@link DefaultService} and {@link FilmService} interface.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Service
@Slf4j
public class FilmServiceImpl implements DefaultService<Film, Long>, FilmService {

    private final FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public Film findById(Long id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film with id: " + id + " was not found"));
    }

    @Override
    public Film save(Film film) {
        film.setRating(0.0);
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
        filmRepository.delete(film);
        return film;
    }

    @Override
    public List<Film> getAllByGenre(Genre genre) {
        return filmRepository.getAllByGenre(genre);
    }

    @Override
    public Film findByName(String name) {
        return filmRepository.getByName(name);
    }

    @Override
    public Page<Film> getAllByPages(Pageable pageable) {
        return filmRepository.findAll(pageable);
    }

    public Page<Film> search(Pageable pageable, String name, Integer year, Double price) {
        return filmRepository.advancedSearch(pageable, name, year, price);
    }

}
