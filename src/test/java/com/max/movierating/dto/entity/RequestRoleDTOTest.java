package com.max.movierating.dto.entity;

import com.max.movierating.dto.entity.RequestRoleDTO;
import com.max.movierating.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestRoleDTOTest {

    private final RequestRoleDTO requestRoleDTO = new RequestRoleDTO();

    @BeforeEach
    void setUp() {
        requestRoleDTO.setName("TEST");
    }


    @Test
    void toRole() {
        Role role = Role.builder().name("TEST").build();
        assertEquals(role, requestRoleDTO.toRole());
    }

    @Test
    void getName() {
        assertEquals("TEST", requestRoleDTO.getName());
    }

    @Test
    void setName() {
        requestRoleDTO.setName("TEST1");
        assertEquals("TEST1", requestRoleDTO.getName());
    }
}