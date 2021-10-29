package com.max.movierating.repository;

import com.max.movierating.entity.Film;
import com.max.movierating.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    List<Film> getAllByGenre(Genre genre);

    Page<Film> findAll(Pageable pageable);

    Film getByName(String name);



}
