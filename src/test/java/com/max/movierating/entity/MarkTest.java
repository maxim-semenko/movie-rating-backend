package com.max.movierating.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkTest {

    private final Mark mark = new Mark();
    private final User user = User.builder().username("username").build();
    private final Film film = Film.builder().name("name").build();


    @BeforeEach
    void setUp() {
        mark.setId(1L);
        mark.setUser(user);
        mark.setFilm(film);
        mark.setValue(10);
    }

    @Test
    void getUser() {
        assertEquals(user, mark.getUser());
    }

    @Test
    void getFilm() {
        assertEquals(film, mark.getFilm());
    }

    @Test
    void getValue() {
        assertEquals(10, mark.getValue());
    }

    @Test
    void setUser() {
        final User user = User.builder().username("username1").build();
        mark.setUser(user);
        assertEquals(user, mark.getUser());
    }

    @Test
    void setFilm() {
        final Film film = Film.builder().name("name1").build();
        mark.setFilm(film);
        assertEquals(film, mark.getFilm());
    }

    @Test
    void setValue() {
        mark.setValue(2);
        assertEquals(2, mark.getValue());
    }

    @Test
    void testToString() {
        Mark mark1 = Mark.builder().id(1L).user(user).film(film).value(10).build();
        assertEquals(mark.toString(), mark1.toString());
    }

    @Test
    void builder() {
        Mark mark1 = Mark.builder().id(1L).user(user).film(film).value(10).build();
        assertEquals(mark, mark1);
    }
}