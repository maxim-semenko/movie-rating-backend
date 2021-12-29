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

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    List<Film> getAllByGenre(Genre genre);

    Page<Film> findAll(Pageable pageable);

    Page<Film> findAllByNameContaining(Pageable pageable, String name);

    @Query("select e from Film e where" +
            "(:name is null or e.name =:name) " +
            "and (:year is null or e.year=:year) " +
            "and (:price is null or e.price=:price)")
    Page<Film> advancedSearch(Pageable pageable,
                              @Param("name") String name,
                              @Param("year") Integer year,
                              @Param("price") Double price);

    Film getByName(String name);

}
