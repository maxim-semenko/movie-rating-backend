package com.max.movierating.service;

import com.max.movierating.entity.Film;

public interface MarkService {

    Film createMark(Long userId, Long filmId, Integer value);

    Film removeMarkByUserIdAndFilmId(Long userId, Long filmId);

}
