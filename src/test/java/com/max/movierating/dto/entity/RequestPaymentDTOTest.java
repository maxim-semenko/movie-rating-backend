package com.max.movierating.dto.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestPaymentDTOTest {

    private final RequestPaymentDTO requestPaymentDTO = new RequestPaymentDTO();

    @BeforeEach
    void setUp() {
        requestPaymentDTO.setUserId(1L);
        requestPaymentDTO.setEmailCode(123456);
    }

    @Test
    void getUserId() {
        assertEquals(1L, requestPaymentDTO.getUserId());
    }

    @Test
    void getEmailCode() {
        assertEquals(123456, requestPaymentDTO.getEmailCode());
    }

    @Test
    void setUserId() {
        requestPaymentDTO.setUserId(2L);
        assertEquals(2L, requestPaymentDTO.getUserId());
    }

    @Test
    void setEmailCode() {
        requestPaymentDTO.setEmailCode(654321);
        assertEquals(654321, requestPaymentDTO.getEmailCode());
    }
}