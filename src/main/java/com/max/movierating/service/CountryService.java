package com.max.movierating.service;

import com.max.movierating.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryService {

    Page<Country> getAllByPages(Pageable pageable);

}
