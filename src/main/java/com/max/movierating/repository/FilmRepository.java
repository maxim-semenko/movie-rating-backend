package com.max.movierating.repository;

import com.max.movierating.entity.Film;
import com.max.movierating.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Modifying
//    @Query("select f from Film f join  g where g = :genre")

//    @Query("SELECT f FROM Film f JOIN f.genres g WHERE g = :genre ORDER BY f.rating DESC")
    List<Film> findTop3ByGenresOrderByRatingDesc(Genre genre);

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
