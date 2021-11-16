package com.max.movierating.service;

import com.max.movierating.entity.Film;
import com.max.movierating.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FilmService {

    List<Film> getAllByGenre(Genre genre);

    Film findByName(String name);

    Page<Film> getAllByPages(Pageable pageable);

}