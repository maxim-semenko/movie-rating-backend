package com.max.movierating.service.impl;

import com.max.movierating.constant.ErrorConstant;
import com.max.movierating.entity.Genre;
import com.max.movierating.exception.BadRequestException;
import com.max.movierating.exception.ResourceDeleteException;
import com.max.movierating.exception.ResourceNotFoundException;
import com.max.movierating.repository.GenreRepository;
import com.max.movierating.service.DefaultService;
import com.max.movierating.service.GenreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Genre Service implementation that realize DefaultService {@link DefaultService}
 * and GenreService {@link GenreService} interfaces.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Service
@Slf4j
public class GenreServiceImpl implements DefaultService<Genre, Long>, GenreService {

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
        Optional<Genre> genreOptional = genreRepository.findById(id);
        if (genreOptional.isEmpty()) {
            log.error("Genre with id: " + id + " was not found");
            throw new ResourceNotFoundException("Genre with id: " + id + " was not found");
        }

        return genreOptional.get();
    }

    @Override
    public Genre save(Genre genre) {
        if (genreRepository.existsByName(genre.getName())) {
            log.error("Genre are existed already by the name: " + genre.getName());
            throw new BadRequestException("Genre are existed already by the name: " + genre.getName());
        }
        return genreRepository.save(genre);
    }

    @Override
    public Genre update(Genre genre, Long id) {
        if (genreRepository.existsByName(genre.getName())) {
            log.error("Genre are existed already by the name: " + genre.getName());
            throw new BadRequestException("Genre are existed already by the name: " + genre.getName());
        }
        genre.setId(id);
        return genreRepository.save(genre);
    }

    @Override
    public Genre deleteById(Long id) {
        Genre genre = findById(id);
        try {
            genreRepository.delete(genre);
        } catch (DataIntegrityViolationException e) {
            log.error(ErrorConstant.ERROR_CANT_DELETE_GENRE);
            throw new ResourceDeleteException(ErrorConstant.ERROR_CANT_DELETE_GENRE);
        }

        return genre;
    }

    @Override
    public Page<Genre> getAllByPages(Pageable pageable) {
        return genreRepository.findAll(pageable);
    }

    @Override
    public Genre findByName(String name) {
        Optional<Genre> optionalGenre = genreRepository.findByName(name);
        if (optionalGenre.isEmpty()) {
            throw new ResourceNotFoundException("genre not found");
        }

        return optionalGenre.get();
    }
}
