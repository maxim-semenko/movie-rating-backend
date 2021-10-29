package com.max.movierating.service.impl;

import com.max.movierating.entity.Country;
import com.max.movierating.exception.ResourceNotFoundException;
import com.max.movierating.repository.CountryRepository;
import com.max.movierating.service.DefaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements DefaultService<Country, Long> {

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
        return countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country with id: " + id + " was not found"));
    }

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country update(Country country, Long id) {
        country.setId(id);
        return countryRepository.save(country);
    }

    @Override
    public Country deleteById(Long id) {
        Country country = findById(id);
        countryRepository.delete(country);
        return country;
    }
}
