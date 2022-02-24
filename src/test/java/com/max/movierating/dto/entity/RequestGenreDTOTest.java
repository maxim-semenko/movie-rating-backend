package com.max.movierating.dto.entity;

import com.max.movierating.dto.entity.RequestGenreDTO;
import com.max.movierating.entity.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestGenreDTOTest {

    private final RequestGenreDTO requestGenreDTO = new RequestGenreDTO();

    @BeforeEach
    void setUp() {
        requestGenreDTO.setName("FUNNY");
    }

    @Test
    void toGenre() {
        Genre genre = Genre.builder().name("FUNNY").build();
        assertEquals(genre, requestGenreDTO.toGenre());
    }

    @Test
    void getName() {
        assertEquals("FUNNY", requestGenreDTO.getName());
    }

    @Test
    void setName() {
        requestGenreDTO.setName("HORROR");
        assertEquals("HORROR", requestGenreDTO.getName());
    }
}