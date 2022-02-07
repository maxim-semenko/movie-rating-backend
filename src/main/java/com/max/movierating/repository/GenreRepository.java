package com.max.movierating.repository;

import com.max.movierating.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * GenreRepository for working with entity {@link Genre}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Page<Genre> findAll(Pageable pageable);

    Optional<Genre> findByName(String name);
}
