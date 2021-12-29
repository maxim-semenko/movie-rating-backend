package com.max.movierating.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountryTest {

    private final Country country = new Country();

    @BeforeEach
    void setUp() {
        country.setName("testName");
    }

    @Test
    void getName() {
        assertEquals("testName", country.getName());
    }

    @Test
    void setName() {
        country.setName("testName1");
        assertEquals("testName1", country.getName());
    }

    @Test
    void testEquals() {
        final Country country1 = new Country("testName");
        assertEquals(country, country1);
    }

    @Test
    void testToString() {
        final Country country1 = new Country("testName");
        assertEquals(country.toString(), country1.toString());
    }

    @Test
    void testBuilder() {
        Country country1 = Country.builder().name("testName").build();
        assertEquals(country, country1);
    }

}