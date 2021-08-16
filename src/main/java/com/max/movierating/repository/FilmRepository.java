package com.max.movierating.repository;

import com.max.movierating.entity.Film;
import com.max.movierating.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    List<Film> getAllByGenre(Genre genre);

    Film getByName(String name);
}
