package com.max.movierating.repository;

import com.max.movierating.entity.Film;
import com.max.movierating.entity.Mark;
import com.max.movierating.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * MarkRepository for working with entity {@link Mark}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {
    /**
     * Method that get optional mark by user and film.
     *
     * @param user {@link User}
     * @param film {@link Film}
     * @return mark {@link Optional<Mark>}
     */
    Optional<Mark> findByUserAndFilm(User user, Film film);

    void deleteAllByUser(User user);

}
