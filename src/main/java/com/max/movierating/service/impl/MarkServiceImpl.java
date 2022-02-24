package com.max.movierating.service.impl;

import com.max.movierating.constant.ErrorConstant;
import com.max.movierating.dto.entity.RequestMarkDTO;
import com.max.movierating.entity.Film;
import com.max.movierating.entity.Mark;
import com.max.movierating.entity.User;
import com.max.movierating.exception.BadRequestException;
import com.max.movierating.exception.ResourceNotFoundException;
import com.max.movierating.repository.MarkRepository;
import com.max.movierating.service.MarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Role Service implementation that realize MarkService interface {@link MarkService}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Service
@Slf4j
public class MarkServiceImpl implements MarkService {

    private final MarkRepository markRepository;
    private final UserServiceImpl userService;
    private final FilmServiceImpl filmService;

    @Autowired
    public MarkServiceImpl(MarkRepository markRepository,
                           UserServiceImpl userService,
                           FilmServiceImpl filmService) {
        this.markRepository = markRepository;
        this.filmService = filmService;
        this.userService = userService;
    }

    @Override
    public Mark createMark(RequestMarkDTO markDTO) {
        User user = userService.findById(markDTO.getUserId());
        Film film = filmService.findById(markDTO.getFilmId());

        Mark mark;
        Optional<Mark> optionalMark = markRepository.findByUserAndFilm(user, film);

        if (optionalMark.isEmpty()) {
            mark = Mark.builder().user(user).film(film).value(markDTO.getValue()).build();
            markRepository.save(mark);
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
        } else {
            log.error(ErrorConstant.MARK_NOT_FOUND + id);
            throw new ResourceNotFoundException(ErrorConstant.MARK_NOT_FOUND + id);
        }

        return mark;
    }

    @Override
    public Mark findMarkByUserIdAndFilmId(Long userId, Long filmId) {
        User user = userService.findById(userId);
        Film film = filmService.findById(filmId);

        Optional<Mark> optionalMark = markRepository.findByUserAndFilm(user, film);
        if (optionalMark.isEmpty()) {
            log.error(ErrorConstant.MARK_NOT_FOUND + "user = " + userId + ", film = " + filmId);
            throw new ResourceNotFoundException(ErrorConstant.MARK_NOT_FOUND + "user = " + userId + ", film = " + filmId);
        }
        return optionalMark.get();
    }
}
