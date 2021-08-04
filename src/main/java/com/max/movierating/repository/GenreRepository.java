package com.max.movierating.repository;

import com.max.movierating.entity.EnumGenre;
import com.max.movierating.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByName(EnumGenre name);
}
