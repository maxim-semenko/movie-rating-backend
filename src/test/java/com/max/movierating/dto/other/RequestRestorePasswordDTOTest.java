package com.max.movierating.dto.other;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestRestorePasswordDTOTest {

    private RequestRestorePasswordDTO requestRestorePasswordDTO = new RequestRestorePasswordDTO();

    @BeforeEach
    void setUp() {
        requestRestorePasswordDTO.setNewPassword("password");
        requestRestorePasswordDTO.setEmail("email");
        requestRestorePasswordDTO.setEmailCode(1234);
    }

    @Test
    void getEmail() {
        assertEquals("email", requestRestorePasswordDTO.getEmail());
    }

    @Test
    void getEmailCode() {
        assertEquals(1234, requestRestorePasswordDTO.getEmailCode());
    }

    @Test
    void getNewPassword() {
        assertEquals("password", requestRestorePasswordDTO.getNewPassword());
    }

    @Test
    void setEmail() {
        requestRestorePasswordDTO.setEmail("email1");
        assertEquals("email1", requestRestorePasswordDTO.getEmail());
    }

    @Test
    void setEmailCode() {
        requestRestorePasswordDTO.setEmailCode(4321);
        assertEquals(4321, requestRestorePasswordDTO.getEmailCode());
    }

    @Test
    void setNewPassword() {
        requestRestorePasswordDTO.setNewPassword("password1");
        assertEquals("password1", requestRestorePasswordDTO.getNewPassword());
    }
}
