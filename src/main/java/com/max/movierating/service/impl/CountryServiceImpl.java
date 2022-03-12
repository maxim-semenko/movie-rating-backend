package com.max.movierating.service.impl;

import com.max.movierating.constant.ErrorConstant;
import com.max.movierating.entity.Country;
import com.max.movierating.exception.BadRequestException;
import com.max.movierating.exception.ResourceDeleteException;
import com.max.movierating.exception.ResourceNotFoundException;
import com.max.movierating.repository.CountryRepository;
import com.max.movierating.service.CountryService;
import com.max.movierating.service.DefaultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Country Service implementation that realize defaultService {@link DefaultService}
 * and countryService {@link CountryService} interfaces.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Service
@Slf4j
public class CountryServiceImpl implements DefaultService<Country, Long>, CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        Optional<Country> countryOptional = countryRepository.findById(id);
        if (countryOptional.isEmpty()) {
            log.error("Country with id: " + id + " was not found");
            throw new ResourceNotFoundException("Country with id: " + id + " was not found");
        }

        return countryOptional.get();
    }

    @Override
    public Country save(Country country) {
        if (countryRepository.existsByName(country.getName())) {
            log.error("Country are existed already by the name: " + country.getName());
            throw new BadRequestException("Country are existed already by the name: " + country.getName());
        }
        return countryRepository.save(country);
    }

    @Override
    public Country update(Country country, Long id) {
        if (countryRepository.existsByName(country.getName())) {
            log.error("Country are existed already by the name: " + country.getName());
            throw new BadRequestException("Country are existed already by the name: " + country.getName());
        }
        country.setId(id);
        return countryRepository.save(country);
    }

    @Override
    public Country deleteById(Long id) {
        Country country = findById(id);
        try {
            countryRepository.delete(country);
        } catch (DataIntegrityViolationException e) {
            log.error(ErrorConstant.ERROR_CANT_DELETE_COUNTRY);
            throw new ResourceDeleteException(ErrorConstant.ERROR_CANT_DELETE_COUNTRY);
        }

        return country;
    }

    @Override
    public Page<Country> getAllByPages(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }
}
