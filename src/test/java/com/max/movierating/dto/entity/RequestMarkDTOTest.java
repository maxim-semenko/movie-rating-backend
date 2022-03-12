package com.max.movierating.dto.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestMarkDTOTest {

    private final RequestMarkDTO requestMarkDTO = new RequestMarkDTO();

    @BeforeEach
    void setUp() {
        requestMarkDTO.setFilmId(1L);
        requestMarkDTO.setUserId(1L);
        requestMarkDTO.setValue(10);
    }

    @Test
    void getUserId() {
        assertEquals(1L, requestMarkDTO.getUserId());
    }

    @Test
    void getFilmId() {
        assertEquals(1L, requestMarkDTO.getFilmId());
    }

    @Test
    void getValue() {
        assertEquals(10, requestMarkDTO.getValue());
    }

    @Test
    void setUserId() {
        requestMarkDTO.setUserId(2L);
        assertEquals(2L, requestMarkDTO.getUserId());
    }

    @Test
    void setFilmId() {
        requestMarkDTO.setFilmId(2L);
        assertEquals(2L, requestMarkDTO.getFilmId());
    }

    @Test
    void setValue() {
        requestMarkDTO.setValue(9);
        assertEquals(9, requestMarkDTO.getValue());
    }
}