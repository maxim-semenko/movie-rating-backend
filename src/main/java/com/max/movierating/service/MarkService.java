package com.max.movierating.service;

import com.max.movierating.dto.RequestMarkDTO;
import com.max.movierating.entity.Mark;

public interface MarkService {

    Mark createMark(RequestMarkDTO markDTO);

    Mark removeMarkById(Long id);

    Mark findMarkByUserIdAndFilmId(Long userId, Long filmId);

}
