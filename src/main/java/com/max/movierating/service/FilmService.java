package com.max.movierating.service;

import com.max.movierating.entity.Film;
import com.max.movierating.entity.Genre;

import java.util.List;

public interface FilmService {

    List<Film> getAllByGenre(Genre genre);

    Film getByName(String name);

}
