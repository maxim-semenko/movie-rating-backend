package com.max.movierating.entity.mail;

import com.max.movierating.entity.User;
import com.max.movierating.entity.enums.MessageTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MailCodeTest {

    private final MailCode mailCode = new MailCode();

    @BeforeEach
    void setUp() {
        mailCode.setId(1L);
        mailCode.setCode(1234);
        mailCode.setMailTypeMessage(new MailTypeMessage(1L, MessageTypeEnum.PAYMENT_ORDER.toString()));
        mailCode.setUser(null);
        mailCode.setCountAttempts(0);
        mailCode.setIsValid(true);
    }

    @Test
    void getId() {
        assertEquals(1L, mailCode.getId());
    }

    @Test
    void getUser() {
        assertNull(mailCode.getUser());
    }

    @Test
    void getCode() {
        assertEquals(1234, mailCode.getCode());
    }

    @Test
    void getMailTypeMessage() {
        assertEquals(MessageTypeEnum.PAYMENT_ORDER.toString(), mailCode.getMailTypeMessage().getName());
    }

    @Test
    void getIsValid() {
        assertEquals(true, mailCode.getIsValid());
    }

    @Test
    void getCountAttempts() {
        assertEquals(0, mailCode.getCountAttempts());
    }

    @Test
    void setId() {
        mailCode.setId(2L);
        assertEquals(2L, mailCode.getId());
    }

    @Test
    void setUser() {
        mailCode.setUser(new User());
        assertNotNull(mailCode.getUser());
    }

    @Test
    void setCode() {
        mailCode.setCode(4321);
        assertEquals(4321, mailCode.getCode());
    }

    @Test
    void setMailTypeMessage() {
        mailCode.setMailTypeMessage(new MailTypeMessage(1L, MessageTypeEnum.RESTORE_PASSWORD.toString()));
        assertEquals(MessageTypeEnum.RESTORE_PASSWORD.toString(), mailCode.getMailTypeMessage().getName());
    }

    @Test
    void setIsValid() {
        mailCode.setIsValid(false);
        assertEquals(false, mailCode.getIsValid());
    }

    @Test
    void setCountAttempts() {
        mailCode.setCountAttempts(1);
        assertEquals(1, mailCode.getCountAttempts());
    }

    @Test
    void testToString() {
        assertNotNull(mailCode.toString());
    }

    @Test
    void builder() {
        MailCode mailCode1 = MailCode.builder().build();
        assertNotNull(mailCode1);
    }
}