package com.max.movierating.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenreTest {

    private final Genre genre = new Genre(1L, "testName");


    @Test
    void getName() {
        assertEquals("testName", genre.getName());
    }

    @Test
    void setName() {
        genre.setName("testName1");
        assertEquals("testName1", genre.getName());
    }

    @Test
    void testEquals() {
        final Genre genre1 = new Genre(1L, "testName");
        assertEquals(genre, genre1);
    }

    @Test
    void testToString() {
        final Genre genre1 = new Genre(1L, "testName");
        assertEquals(genre.toString(), genre1.toString());
    }

    @Test
    void testBuilder() {
        Genre genre1 = Genre.builder().id(1L).name("testName").build();
        assertEquals(genre, genre1);
    }
}