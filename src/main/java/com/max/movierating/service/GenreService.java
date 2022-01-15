package com.max.movierating.service;

import com.max.movierating.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenreService {

    Page<Genre> getAllByPages(Pageable pageable);

}
