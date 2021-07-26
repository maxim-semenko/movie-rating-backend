package com.max.movierating.service.impl;

import com.max.movierating.entity.Film;
import com.max.movierating.repository.FilmRepository;
import com.max.movierating.service.DefaultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService implements DefaultService<Film> {

    private final FilmRepository filmRepository;

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
