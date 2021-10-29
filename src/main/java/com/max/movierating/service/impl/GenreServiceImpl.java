package com.max.movierating.service.impl;

import com.max.movierating.entity.Genre;
import com.max.movierating.exception.ResourceNotFoundException;
import com.max.movierating.repository.GenreRepository;
import com.max.movierating.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements DefaultService<Genre, Long> {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre findById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Genre with id: " + id + " was not found"));

    }

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre update(Genre genre, Long id) {
        genre.setId(id);
        return genreRepository.save(genre);
    }

    @Override
    public Genre deleteById(Long id) {
        Genre genre = findById(id);
        genreRepository.delete(genre);
        return genre;
    }
}
