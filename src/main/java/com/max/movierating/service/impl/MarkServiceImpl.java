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
    public Mark createMark(Long userId, Long filmId, Integer value) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("user not found!");
        }

        Optional<Film> filmOptional = filmRepository.findById(filmId);
        if (filmOptional.isEmpty()) {
            throw new ResourceNotFoundException("film not found!");
        }

        Mark mark;
        Optional<Mark> optionalMark = markRepository.findByUserIdAndFilmId(userId, filmId);
        if (optionalMark.isEmpty()) {
            if (value > 0 && value < 11) {
                User user = userOptional.get();
                Film film = filmOptional.get();

                mark = Mark.builder().user(user).film(film).value(value).build();
                markRepository.save(mark);

                film.setRating(markRepository.getAverageMarkByFilmId(filmId));
                filmRepository.save(film);
            } else {
                throw new BadRequestException("Invalid value for mark!");
            }
        } else {
            throw new BadRequestException("Can't create mark, its already exist!");
        }

        return mark;
    }

    @Override
    public Mark updateMark(Long userId, Long filmId, Integer value) {
        Mark mark;
        Optional<Mark> optionalMark = markRepository.findByUserIdAndFilmId(userId, filmId);

        if (optionalMark.isPresent()) {
            if (value > 0 && value < 11) {
                mark = optionalMark.get();
                mark.setValue(value);
                markRepository.save(mark);

                Film film = filmRepository.getById(filmId);
                film.setRating(markRepository.getAverageMarkByFilmId(filmId));
                filmRepository.save(film);
            } else {
                throw new BadRequestException("Invalid value for mark!");
            }
        } else {
            throw new ResourceNotFoundException("Mark not found!");
        }

        return mark;
    }

    @Override
    public Mark removeMarkByUserIdAndFilmId(Long userId, Long filmId) {
        Mark mark;
        Optional<Mark> optionalMark = markRepository.findByUserIdAndFilmId(userId, filmId);
        if (optionalMark.isPresent()) {
            mark = optionalMark.get();
            markRepository.delete(mark);

            Film film = filmRepository.getById(filmId);
            film.setRating(markRepository.getAverageMarkByFilmId(filmId));
            filmRepository.save(film);
        } else {
            throw new ResourceNotFoundException("Mark not found!");
        }

        return mark;
    }
}
