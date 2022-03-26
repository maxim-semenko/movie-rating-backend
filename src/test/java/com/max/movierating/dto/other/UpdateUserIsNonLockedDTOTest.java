package com.max.movierating.dto.other;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateUserIsNonLockedDTOTest {

    private UpdateUserIsNonLockedDTO updateUserIsNonLockedDTO = new UpdateUserIsNonLockedDTO();

    @BeforeEach
    void setUp() {
        updateUserIsNonLockedDTO.setIsNonLocked(false);
    }

    @Test
    void getIsNonLocked() {
        assertEquals(false, updateUserIsNonLockedDTO.getIsNonLocked());
    }

    @Test
    void setIsNonLocked() {
        updateUserIsNonLockedDTO.setIsNonLocked(true);
        assertEquals(true, updateUserIsNonLockedDTO.getIsNonLocked());
    }
}
