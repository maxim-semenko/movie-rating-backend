package com.max.movierating.dto.entity;

import com.max.movierating.entity.Country;
import com.max.movierating.entity.Genre;
import com.max.movierating.entity.enums.RoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RequestFilmDTOTest {

    private final RequestFilmDTO requestFilmDTO = new RequestFilmDTO();

    @BeforeEach
    void setUp() {
        requestFilmDTO.setName("name_test");
        requestFilmDTO.setDescription("description_test");
        requestFilmDTO.setPrice(1.99);
        requestFilmDTO.setYear(2000);
        requestFilmDTO.setTimeInMinutes(100);
        requestFilmDTO.setImageURL("url");
        requestFilmDTO.setCountries(Set.of(new Country(1L, "TEST")));
        requestFilmDTO.setGenres(Set.of(new Genre(1L, "TEST")));
    }

    @Test
    void toFilm() {
        assertNotNull(requestFilmDTO.toFilm());
    }

    @Test
    void getName() {
        assertEquals("name_test", requestFilmDTO.getName());
    }

    @Test
    void getDescription() {
        assertEquals("description_test", requestFilmDTO.getDescription());
    }

    @Test
    void getImageURL() {
        assertEquals("url", requestFilmDTO.getImageURL());
    }

    @Test
    void getTimeInMinutes() {
        assertEquals(100, requestFilmDTO.getTimeInMinutes());
    }

    @Test
    void getYear() {
        assertEquals(2000, requestFilmDTO.getYear());
    }

    @Test
    void getPrice() {
        assertEquals(1.99, requestFilmDTO.getPrice());
    }

    @Test
    void getGenres() {
        assertNotNull(requestFilmDTO.getGenres());
    }

    @Test
    void getCountries() {
        assertNotNull(requestFilmDTO.getCountries());
    }

    @Test
    void setName() {
        requestFilmDTO.setName("name_test1");
        assertEquals("name_test1", requestFilmDTO.getName());
    }

    @Test
    void setDescription() {
        requestFilmDTO.setDescription("description_test1");
        assertEquals("description_test1", requestFilmDTO.getDescription());
    }

    @Test
    void setImageURL() {
        requestFilmDTO.setImageURL("url1");
        assertEquals("url1", requestFilmDTO.getImageURL());
    }

    @Test
    void setTimeInMinutes() {
        requestFilmDTO.setTimeInMinutes(0);
        assertEquals(0, requestFilmDTO.getTimeInMinutes());
    }

    @Test
    void setYear() {
        requestFilmDTO.setYear(1000);
        assertEquals(1000, requestFilmDTO.getYear());
    }

    @Test
    void setPrice() {
        requestFilmDTO.setPrice(2.99);
        assertEquals(2.99, requestFilmDTO.getPrice());
    }

    @Test
    void setGenres() {
        requestFilmDTO.setGenres(null);
        assertNull(requestFilmDTO.getGenres());
    }

    @Test
    void setCountries() {
        requestFilmDTO.setCountries(null);
        assertNull(requestFilmDTO.getCountries());
    }
}