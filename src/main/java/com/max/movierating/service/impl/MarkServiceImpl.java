package com.max.movierating.service.impl;

import com.max.movierating.entity.Film;
import com.max.movierating.entity.Mark;
import com.max.movierating.entity.User;
import com.max.movierating.exception.BadRequestException;
import com.max.movierating.exception.ResourceNotFoundException;
import com.max.movierating.repository.FilmRepository;
import com.max.movierating.repository.MarkRepository;
import com.max.movierating.repository.UserRepository;
import com.max.movierating.service.MarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class MarkServiceImpl implements MarkService {

    private final MarkRepository markRepository;
    private final FilmRepository filmRepository;
    private final UserRepository userRepository;

    @Autowired
    public MarkServiceImpl(MarkRepository markRepository,
                           FilmRepository filmRepository,
                           UserRepository userRepository) {
        this.markRepository = markRepository;
        this.filmRepository = filmRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Film createMark(Long userId, Long filmId, Integer value) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("user not found!");
        }

        Optional<Film> filmOptional = filmRepository.findById(filmId);
        if (filmOptional.isEmpty()) {
            throw new ResourceNotFoundException("film not found!");
        }

        User user = userOptional.get();
        Film film = filmOptional.get();

        Optional<Mark> optionalMark = markRepository.findByUserIdAndFilmId(userId, filmId);
        if (optionalMark.isEmpty()) {
            markRepository.save(new Mark(user, film, value));
            film.setRating(markRepository.getAverageMarkByFilmId(filmId));
            filmRepository.save(film);
        } else {
            throw new BadRequestException("Can't create mark, its already exist!");
        }

        return film;
    }

    @Override
    public Film removeMarkByUserIdAndFilmId(Long userId, Long filmId) {
        return null;
    }
}
