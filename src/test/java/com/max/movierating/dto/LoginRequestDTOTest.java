package com.max.movierating.dto;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestDTOTest {

    private final LoginRequestDTO loginRequestDTO = new LoginRequestDTO();

    @BeforeEach
    void setUp() {
        loginRequestDTO.setUsername("username");
        loginRequestDTO.setPassword("password");
    }

    @Test
    void getUsername() {
        Assert.assertEquals("username", loginRequestDTO.getUsername());
    }

    @Test
    void getPassword() {
        Assert.assertEquals("password", loginRequestDTO.getPassword());
    }

    @Test
    void setUsername() {
        loginRequestDTO.setUsername("username1");
        Assert.assertEquals("username1", loginRequestDTO.getUsername());

    }

    @Test
    void setPassword() {
        loginRequestDTO.setPassword("password1");
        Assert.assertEquals("password1", loginRequestDTO.getPassword());
    }
}