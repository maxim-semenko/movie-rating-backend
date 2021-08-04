package com.max.movierating.service.impl;

import com.max.movierating.entity.EnumGenre;
import com.max.movierating.entity.Film;
import com.max.movierating.repository.FilmRepository;
import com.max.movierating.repository.GenreRepository;
import com.max.movierating.service.DefaultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements DefaultService<Film> {

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
}
