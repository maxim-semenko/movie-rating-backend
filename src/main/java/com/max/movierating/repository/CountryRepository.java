package com.max.movierating.repository;

import com.max.movierating.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CountryRepository for working with entity {@link Country}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    /**
     * Method that finds all countries by pageable.
     *
     * @param pageable contain any params (size, page, etc)
     * @return page of countries
     */
    Page<Country> findAll(Pageable pageable);

}
