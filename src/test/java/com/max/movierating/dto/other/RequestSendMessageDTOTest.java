package com.max.movierating.dto.other;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestSendMessageDTOTest {

    RequestSendMessageDTO requestSendMessageDTO = new RequestSendMessageDTO();

    @BeforeEach
    void setUp() {
        requestSendMessageDTO.setTypeMessage("TEST");
        requestSendMessageDTO.setEmail("email");
    }

    @Test
    void getEmail() {
        assertEquals("email", requestSendMessageDTO.getEmail());
    }

    @Test
    void getTypeMessage() {
        assertEquals("TEST", requestSendMessageDTO.getTypeMessage());
    }

    @Test
    void setEmail() {
        requestSendMessageDTO.setEmail("email1");
        assertEquals("email1", requestSendMessageDTO.getEmail());
    }

    @Test
    void setTypeMessage() {
        requestSendMessageDTO.setTypeMessage("TEST1");
        assertEquals("TEST1", requestSendMessageDTO.getTypeMessage());
    }
}
