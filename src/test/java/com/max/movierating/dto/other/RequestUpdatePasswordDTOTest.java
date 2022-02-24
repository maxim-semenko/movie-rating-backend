package com.max.movierating.dto.other;

import com.max.movierating.dto.other.UpdatePasswordDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestUpdatePasswordDTOTest {

    private final UpdatePasswordDTO requestUpdatePasswordDTO = new UpdatePasswordDTO();

    @BeforeEach
    void setUp() {
        requestUpdatePasswordDTO.setOldPassword("1234");
        requestUpdatePasswordDTO.setNewPassword("4321");
    }

    @Test
    void getOldPassword() {
        assertEquals("1234", requestUpdatePasswordDTO.getOldPassword());
    }

    @Test
    void getNewPassword() {
        assertEquals("4321", requestUpdatePasswordDTO.getNewPassword());
    }

    @Test
    void setOldPassword() {
        requestUpdatePasswordDTO.setOldPassword("5678");
        assertEquals("5678", requestUpdatePasswordDTO.getOldPassword());
    }

    @Test
    void setNewPassword() {
        requestUpdatePasswordDTO.setNewPassword("8765");
        assertEquals("8765", requestUpdatePasswordDTO.getNewPassword());
    }
}