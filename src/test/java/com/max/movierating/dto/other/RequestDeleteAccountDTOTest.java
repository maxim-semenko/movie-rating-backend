package com.max.movierating.dto.other;

import com.max.movierating.dto.other.RequestDeleteAccountDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestDeleteAccountDTOTest {

    private final RequestDeleteAccountDTO requestDeleteAccountDTO = new RequestDeleteAccountDTO();

    @BeforeEach
    void setUp() {
        requestDeleteAccountDTO.setPassword("12345678");
    }

    @Test
    void getPassword() {
        assertEquals("12345678", requestDeleteAccountDTO.getPassword());
    }

    @Test
    void setPassword() {
        requestDeleteAccountDTO.setPassword("87654321");
        assertEquals("87654321", requestDeleteAccountDTO.getPassword());
    }
}