package com.max.movierating.dto;

import com.max.movierating.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals("usernameTest1", requestRegisterDTO.getUsername());
    }

    @Test
    void setPassword() {
        requestRegisterDTO.setPassword("passwordTest1");
        assertEquals("passwordTest1", requestRegisterDTO.getPassword());
    }

    @Test
    void setFirstname() {
        requestRegisterDTO.setFirstname("firstnameTest1");
        assertEquals("firstnameTest1", requestRegisterDTO.getFirstname());
    }

    @Test
    void setLastname() {
        requestRegisterDTO.setLastname("lastnameTest1");
        assertEquals("lastnameTest1", requestRegisterDTO.getLastname());
    }

    @Test
    void setEmail() {
        requestRegisterDTO.setEmail("emailTest1");
        assertEquals("emailTest1", requestRegisterDTO.getEmail());
    }

    @Test
    void getUsername() {
        assertEquals("usernameTest", requestRegisterDTO.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("passwordTest", requestRegisterDTO.getPassword());
    }

    @Test
    void getFirstname() {
        assertEquals("firstnameTest", requestRegisterDTO.getFirstname());
    }

    @Test
    void getLastname() {
        assertEquals("lastnameTest", requestRegisterDTO.getLastname());
    }

    @Test
    void getEmail() {
        assertEquals("emailTest", requestRegisterDTO.getEmail());
    }

    @Test
    void toUser() {
        User user = new User();
        user.setFirstname("firstnameTest");
        user.setLastname("lastnameTest");
        user.setUsername("usernameTest");
        user.setEmail("emailTest");
        user.setPassword("passwordTest");
        assertEquals(user, requestRegisterDTO.toUser());
    }
}