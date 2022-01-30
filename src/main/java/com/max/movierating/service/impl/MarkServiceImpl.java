package com.max.movierating.service.impl;

import com.max.movierating.constant.ErrorConstant;
import com.max.movierating.dto.RequestMarkDTO;
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
    public Mark createMark(RequestMarkDTO markDTO) {
        Long userId = markDTO.getUserId();
        Long filmId = markDTO.getFilmId();

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
            User user = userOptional.get();
            Film film = filmOptional.get();

            mark = Mark.builder().user(user).film(film).value(markDTO.getValue()).build();
            markRepository.save(mark);

            film.setRating(markRepository.getAverageMarkByFilmId(filmId));
            filmRepository.save(film);
        } else {
            throw new BadRequestException("Can't create mark, its already exist!");
        }

        return mark;
    }

    @Override
    public Mark removeMarkById(Long id) {
        Mark mark;
        Optional<Mark> markOptional = markRepository.findById(id);

        if (markOptional.isPresent()) {
            mark = markOptional.get();
            markRepository.delete(mark);

            Long filmId = mark.getFilm().getId();
            Film film = filmRepository.getById(filmId);
            Double rating = markRepository.getAverageMarkByFilmId(filmId);
            if (rating == null) {
                rating = 0.0;
            }
            film.setRating(rating);
            filmRepository.save(film);
        } else {
            log.error("Mark not found");
            throw new ResourceNotFoundException(ErrorConstant.MARK_NOT_FOUND + id);
        }

        return mark;
    }

    @Override
    public Mark findMarkByUserIdAndFilmId(Long userId, Long filmId) {
        Optional<Mark> optionalMark = markRepository.findByUserIdAndFilmId(userId, filmId);
        if (optionalMark.isEmpty()) {
            log.error("Mark not found");
            throw new ResourceNotFoundException(ErrorConstant.MARK_NOT_FOUND + "user: " + userId + ", film: " + filmId);
        }
        return optionalMark.get();
    }
}
