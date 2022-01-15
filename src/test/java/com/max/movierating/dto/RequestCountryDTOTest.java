package com.max.movierating.dto;

import com.max.movierating.entity.Country;
import com.max.movierating.entity.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestCountryDTOTest {

    private final RequestCountryDTO requestCountryDTO = new RequestCountryDTO();

    @BeforeEach
    void setUp() {
        requestCountryDTO.setName("TEST");
    }


    @Test
    void toCountry() {
        Country country = Country.builder().name("TEST").build();
        assertEquals(country, requestCountryDTO.toCountry());
    }

    @Test
    void getName() {
        assertEquals("TEST", requestCountryDTO.getName());
    }

    @Test
    void setName() {
        requestCountryDTO.setName("TEST1");
        assertEquals("TEST1", requestCountryDTO.getName());
    }
}