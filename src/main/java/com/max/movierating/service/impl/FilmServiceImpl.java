package com.max.movierating.service.impl;

import com.max.movierating.entity.EnumGenre;
import com.max.movierating.entity.Film;
import com.max.movierating.entity.Genre;
import com.max.movierating.repository.FilmRepository;
import com.max.movierating.repository.GenreRepository;
import com.max.movierating.service.DefaultService;
import com.max.movierating.service.FilmService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class FilmServiceImpl implements DefaultService<Film>, FilmService {

    private final FilmRepository filmRepository;
    private final GenreRepository genreRepository;

    @Override
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    @Override
    public Film findById(Long id) {
        return filmRepository.getById(id);
    }

    @Override
    public Film save(Film film) {
        film.setGenre(genreRepository.findByName(EnumGenre.ACTION));
        return filmRepository.save(film);
    }

    @Override
    public Film update(Film film) {
        return filmRepository.save(film);
    }

    @Override
    public Long deleteById(Long id) {
        filmRepository.deleteById(id);
        return id;
    }

    @Override
    public List<Film> getAllByGenre(Genre genre) {
        return filmRepository.getAllByGenre(genre);
    }

    @Override
    public Film getByName(String name) {
        return filmRepository.getByName(name);
    }
}
