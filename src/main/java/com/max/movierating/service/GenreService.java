package com.max.movierating.service;

import com.max.movierating.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GenreService {

    Page<Genre> getAllByPages(Pageable pageable);

    Genre findByName(String name);

}
