package com.max.movierating.dto;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginRequestDTOTest {

    private final LoginRequestDTO loginRequestDTO = new LoginRequestDTO();

    @BeforeEach
    void setUp() {
        loginRequestDTO.setUsername("test");
        loginRequestDTO.setPassword("test1234");
    }

    @Test
    void getUsername() {
        Assert.assertEquals( "test", loginRequestDTO.getUsername());
    }

    @Test
    void getPassword() {
        Assert.assertEquals( "test1234", loginRequestDTO.getPassword());
    }

    @Test
    void setUsername() {
//        loginRequestDTO.setUsername("username");
//        Assert.assertEquals( "username", loginRequestDTO.getUsername());

    }

    @Test
    void setPassword() {
//        loginRequestDTO.setPassword("password");
//        Assert.assertEquals( "password", loginRequestDTO.getPassword());

    }
}