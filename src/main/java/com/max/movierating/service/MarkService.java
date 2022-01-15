package com.max.movierating.service;

import com.max.movierating.entity.Mark;

public interface MarkService {

    Mark createMark(Long userId, Long filmId, Integer value);

    Mark updateMark(Long userId, Long filmId, Integer value);

    Mark removeMarkByUserIdAndFilmId(Long userId, Long filmId);

}
