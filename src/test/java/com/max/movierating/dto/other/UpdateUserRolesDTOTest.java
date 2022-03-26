package com.max.movierating.dto.other;

import com.max.movierating.entity.Role;
import com.max.movierating.entity.enums.RoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UpdateUserRolesDTOTest {

    private final UpdateUserRolesDTO updateUserRolesDTO = new UpdateUserRolesDTO();

    @BeforeEach
    void setUp() {
        updateUserRolesDTO.setRoles(null);
    }

    @Test
    void getRoles() {
        assertNull(updateUserRolesDTO.getRoles());
    }

    @Test
    void setRoles() {
        updateUserRolesDTO.setRoles(Set.of(new Role(1L, RoleEnum.ROLE_ADMIN.toString())));
        assertEquals(Set.of(new Role(1L, RoleEnum.ROLE_ADMIN.toString())), updateUserRolesDTO.getRoles());
    }
}
