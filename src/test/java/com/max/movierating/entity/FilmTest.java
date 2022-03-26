package com.max.movierating.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FilmTest {

    private final Film film = new Film();

    @BeforeEach
    void setUp() {
        film.setId(1L);
        film.setName("name_test");
        film.setDescription("description_test");
        film.setRating(10.0);
        film.setCountries(Set.of(new Country()));
        film.setGenres(Set.of(new Genre()));
        film.setImageURL("image_test");
        film.setPrice(0.99);
        film.setYear(2000);
        film.setTimeInMinutes(100);
    }

    @Test
    void testToString() {
    }

    @Test
    void testEquals() {
        Film film1 = new Film();
        film1.setName("test");

        Film film2 = new Film();
        film2.setName("test");

        assertEquals(film1, film2);
    }

    @Test
    void getId() {
        assertEquals(1L, film.getId());
    }

    @Test
    void getName() {
        assertEquals("name_test", film.getName());
    }

    @Test
    void getDescription() {
        assertEquals("description_test", film.getDescription());
    }

    @Test
    void getImageURL() {
        assertEquals("image_test", film.getImageURL());
    }

    @Test
    void getTimeInMinutes() {
        assertEquals(100, film.getTimeInMinutes());
    }

    @Test
    void getYear() {
        assertEquals(2000, film.getYear());
    }

    @Test
    void getRating() {
        assertEquals(10.0, film.getRating());
    }

    @Test
    void getPrice() {
        assertEquals(0.99, film.getPrice());
    }

    @Test
    void getGenres() {
        assertEquals(Set.of(new Genre()), film.getGenres());
    }

    @Test
    void getCountries() {
        assertEquals(Set.of(new Country()), film.getCountries());
    }

    @Test
    void setId() {
        film.setId(2L);
        assertEquals(2L, film.getId());
    }

    @Test
    void setName() {
        film.setName("name_test1");
        assertEquals("name_test1", film.getName());
    }

    @Test
    void setDescription() {
        film.setDescription("description_test1");
        assertEquals("description_test1", film.getDescription());
    }

    @Test
    void setImageURL() {
        film.setImageURL("image_url1");
        assertEquals("image_url1", film.getImageURL());
    }

    @Test
    void setTimeInMinutes() {
        film.setTimeInMinutes(99);
        assertEquals(99, film.getTimeInMinutes());
    }

    @Test
    void setYear() {
        film.setYear(1999);
        assertEquals(1999, film.getYear());
    }

    @Test
    void setRating() {
        film.setRating(9.0);
        assertEquals(9.0, film.getRating());
    }

    @Test
    void setPrice() {
        film.setPrice(1.99);
        assertEquals(1.99, film.getPrice());
    }

    @Test
    void setGenres() {
        film.setGenres(null);
        assertNull(film.getGenres());
    }

    @Test
    void setCountries() {
        film.setCountries(null);
        assertNull(film.getCountries());
    }

    @Test
    void builder() {
        Film film1 = Film.builder()
                .id(1L)
                .name("name_test")
                .description("description_test")
                .imageURL("image_test")
                .timeInMinutes(100)
                .year(2000)
                .price(0.99)
                .rating(10.0)
                .countries(Set.of(new Country()))
                .genres(Set.of(new Genre()))
                .build();

        assertEquals(film, film1);
    }
}
