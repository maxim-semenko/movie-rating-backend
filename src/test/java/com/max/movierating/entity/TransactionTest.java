package com.max.movierating.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    private final Transaction transaction = new Transaction();

    @BeforeEach
    void setUp() {
        transaction.setId(1L);
        transaction.setDate(null);
        transaction.setSumma(1.0);
        transaction.setUser(null);
        transaction.setFilmList(null);
        transaction.setTransactionStatus(null);
    }

    @Test
    void testEquals() {
        Transaction transaction1 = new Transaction();
        transaction1.setId(1L);

        Transaction transaction2 = new Transaction();
        transaction2.setId(1L);

        assertEquals(transaction1, transaction2);
    }

    @Test
    void getId() {
        assertEquals(1L, transaction.getId());
    }

    @Test
    void getUser() {
        assertNull(transaction.getUser());
    }

    @Test
    void getSumma() {
        assertEquals(1.0, transaction.getSumma());
    }

    @Test
    void getFilmList() {
        assertNull(transaction.getFilmList());
    }

    @Test
    void getTransactionStatus() {
        assertNull(transaction.getTransactionStatus());
    }

    @Test
    void getDate() {
        assertNull(transaction.getDate());
    }

    @Test
    void setId() {
        transaction.setId(2L);
        assertEquals(2L, transaction.getId());
    }

    @Test
    void setUser() {
        transaction.setUser(new User());
        assertEquals(new User(), transaction.getUser());
    }

    @Test
    void setSumma() {
        transaction.setSumma(2.0);
        assertEquals(2.0, transaction.getSumma());
    }

    @Test
    void setFilmList() {
        transaction.setFilmList(Set.of(new Film()));
        assertEquals(Set.of(new Film()), transaction.getFilmList());
    }

    @Test
    void setTransactionStatus() {
        transaction.setTransactionStatus(new TransactionStatus());
        assertEquals(new TransactionStatus(), transaction.getTransactionStatus());
    }

    @Test
    void setDate() {
        transaction.setDate(new Date());
        assertEquals(new Date(), transaction.getDate());
    }

    @Test
    void builder() {
        Transaction transaction1 = Transaction.builder()
                .id(1L)
                .date(null)
                .transactionStatus(null)
                .filmList(null)
                .summa(1.0)
                .user(null)
                .build();
        assertEquals(transaction1, transaction);
    }
}
