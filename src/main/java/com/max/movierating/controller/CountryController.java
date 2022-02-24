package com.max.movierating.controller;

import com.max.movierating.constant.APIConstant;
import com.max.movierating.dto.entity.RequestCountryDTO;
import com.max.movierating.entity.Country;
import com.max.movierating.service.impl.CountryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * REST controller for countries requests.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@RestController
@RequestMapping(value = APIConstant.COUNTRIES_API)
public class CountryController {

    private final CountryServiceImpl countryService;

    @Autowired
    public CountryController(CountryServiceImpl countryService) {
        this.countryService = countryService;
    }

    /**
     * Method that returns all countries {@link Country}.
     *
     * @return countries {@link Country}
     */
    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Country>> findAllByPages(Pageable pageable) {
        return new ResponseEntity<>(countryService.getAllByPages(pageable), HttpStatus.OK);
    }

    /**
     * Method that returns country by id {@link Country}.
     *
     * @return country {@link Country}
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Country> findById(@PathVariable Long id) {
        return new ResponseEntity<>(countryService.findById(id), HttpStatus.OK);
    }

    /**
     * Method that create a new country {@link Country}.
     *
     * @return country {@link Country}
     */
    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Country> create(@Valid @RequestBody RequestCountryDTO countryDTO) {
        return new ResponseEntity<>(countryService.save(countryDTO.toCountry()), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Country> update(@PathVariable Long id, @Valid @RequestBody RequestCountryDTO countryDTO) {
        return new ResponseEntity<>(countryService.update(countryDTO.toCountry(), id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Country> delete(@PathVariable Long id) {
        return new ResponseEntity<>(countryService.deleteById(id), HttpStatus.OK);
    }

}
