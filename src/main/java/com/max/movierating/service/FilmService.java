package com.max.movierating.service;

import com.max.movierating.entity.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FilmService {

    List<Film> findAllByGenre(String genreName);

    Film findByName(String name);

    Page<Film> getAllByPages(Pageable pageable);

    Page<Film> findAllByName(Pageable pageable, String name);


}