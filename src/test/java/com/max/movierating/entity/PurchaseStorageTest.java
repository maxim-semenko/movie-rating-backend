package com.max.movierating.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseStorageTest {

    private final PurchaseStorage purchaseStorage = new PurchaseStorage();

    @BeforeEach
    void setUp() {
        purchaseStorage.setId(1L);
        purchaseStorage.setUser(null);
        purchaseStorage.setFilmList(null);
    }

    @Test
    void testEquals() {
        PurchaseStorage purchaseStorage1 = new PurchaseStorage();
        purchaseStorage1.setId(1L);

        PurchaseStorage purchaseStorage2 = new PurchaseStorage();
        purchaseStorage2.setId(1L);

        assertEquals(purchaseStorage1, purchaseStorage2);
    }

    @Test
    void getId() {
        assertEquals(1L, purchaseStorage.getId());
    }

    @Test
    void getUser() {
        assertNull(purchaseStorage.getUser());
    }

    @Test
    void getFilmList() {
        assertNull(purchaseStorage.getFilmList());
    }

    @Test
    void setId() {
        purchaseStorage.setId(2L);
        assertEquals(2L, purchaseStorage.getId());
    }

    @Test
    void setUser() {
        purchaseStorage.setUser(new User());
        assertEquals(new User(), purchaseStorage.getUser());
    }

    @Test
    void setFilmList() {
        purchaseStorage.setFilmList(Set.of(new Film()));
        assertEquals(Set.of(new Film()), purchaseStorage.getFilmList());

    }

    @Test
    void builder() {
        PurchaseStorage purchaseStorage1 = PurchaseStorage.builder()
                .id(1L)
                .user(null)
                .filmList(null)
                .build();
        assertEquals(purchaseStorage1, purchaseStorage);
    }
}
