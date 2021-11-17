package com.max.movierating.dto;

import com.max.movierating.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestRegisterDTOTest {

    private final RequestRegisterDTO requestRegisterDTO = new RequestRegisterDTO();

    @BeforeEach
    void setUp() {
        requestRegisterDTO.setFirstname("firstnameTest");
        requestRegisterDTO.setLastname("lastnameTest");
        requestRegisterDTO.setUsername("usernameTest");
        requestRegisterDTO.setEmail("emailTest");
        requestRegisterDTO.setPassword("passwordTest");
    }

    @Test
    void setUsername() {
        requestRegisterDTO.setUsername("usernameTest1");
        Assert.assertEquals("usernameTest1", requestRegisterDTO.getUsername());
    }

    @Test
    void setPassword() {
        requestRegisterDTO.setPassword("passwordTest1");
        Assert.assertEquals("passwordTest1", requestRegisterDTO.getPassword());
    }

    @Test
    void setFirstname() {
        requestRegisterDTO.setFirstname("firstnameTest1");
        Assert.assertEquals("firstnameTest1", requestRegisterDTO.getFirstname());
    }

    @Test
    void setLastname() {
        requestRegisterDTO.setLastname("lastnameTest1");
        Assert.assertEquals("lastnameTest1", requestRegisterDTO.getLastname());
    }

    @Test
    void setEmail() {
        requestRegisterDTO.setEmail("emailTest1");
        Assert.assertEquals("emailTest1", requestRegisterDTO.getEmail());
    }

    @Test
    void getUsername() {
        Assert.assertEquals("usernameTest", requestRegisterDTO.getUsername());
    }

    @Test
    void getPassword() {
        Assert.assertEquals("passwordTest", requestRegisterDTO.getPassword());
    }

    @Test
    void getFirstname() {
        Assert.assertEquals("firstnameTest", requestRegisterDTO.getFirstname());
    }

    @Test
    void getLastname() {
        Assert.assertEquals("lastnameTest", requestRegisterDTO.getLastname());
    }

    @Test
    void getEmail() {
        Assert.assertEquals("emailTest", requestRegisterDTO.getEmail());
    }

    @Test
    void toUser() {
        User user = new User();
        user.setFirstname("firstnameTest");
        user.setLastname("lastnameTest");
        user.setUsername("usernameTest");
        user.setEmail("emailTest");
        user.setPassword("passwordTest");
        Assert.assertEquals(user, requestRegisterDTO.toUser());
    }
}