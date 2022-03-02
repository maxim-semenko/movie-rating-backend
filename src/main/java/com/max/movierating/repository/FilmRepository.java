package com.max.movierating.repository;

import com.max.movierating.entity.Film;
import com.max.movierating.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * FilmRepository for working with entity {@link Film}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    /**
     * Method that find's top 9 films by genre order by decs.
     *
     * @param genre {@link Genre}
     * @return list of films
     */
    List<Film> findTop9ByGenresOrderByRatingDesc(Genre genre);

    /**
     * Method that finds all genre by pageable.
     *
     * @param pageable contain any params (size, page, etc)
     * @return page of films
     */
    Page<Film> findAll(Pageable pageable);

    /**
     * Method that finds all films by name.
     *
     * @param pageable contain any params (size, page, etc)
     * @param name     params for search
     * @return page of films
     */
    Page<Film> findAllByNameContaining(Pageable pageable, String name);

}
