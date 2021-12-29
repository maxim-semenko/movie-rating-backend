package com.max.movierating.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestLoginDTOTest {

    private final RequestLoginDTO loginRequestDTO = new RequestLoginDTO();

    @BeforeEach
    void setUp() {
        loginRequestDTO.setUsername("username");
        loginRequestDTO.setPassword("password");
    }

    @Test
    void getUsername() {
        assertEquals("username", loginRequestDTO.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("password", loginRequestDTO.getPassword());
    }

    @Test
    void setUsername() {
        loginRequestDTO.setUsername("username1");
        assertEquals("username1", loginRequestDTO.getUsername());
    }

    @Test
    void setPassword() {
        loginRequestDTO.setPassword("password1");
        assertEquals("password1", loginRequestDTO.getPassword());
    }
}