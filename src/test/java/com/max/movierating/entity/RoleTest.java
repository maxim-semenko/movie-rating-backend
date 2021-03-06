package com.max.movierating.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleTest {

    private final Role role = new Role(1L, "testName");

    @Test
    void testEquals() {
        final Role role1 = new Role(1L, "testName");
        assertEquals(role, role1);
    }

    @Test
    void getName() {
        assertEquals("testName", role.getName());
    }

    @Test
    void setName() {
        role.setName("testName1");
        assertEquals("testName1", role.getName());
    }

    @Test
    void testToString() {
        final Role role1 = new Role(1L, "testName");
        assertEquals(role.toString(), role1.toString());
    }

    @Test
    void testBuilder() {
        final Role role1 = new Role(1L, "testName");
        assertEquals(role, role1);
    }
}