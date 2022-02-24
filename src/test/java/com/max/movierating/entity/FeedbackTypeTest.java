package com.max.movierating.entity;

import com.max.movierating.entity.enums.FeedbackTypeEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FeedbackTypeTest {

    private final FeedbackType feedbackType = new FeedbackType();

    @BeforeEach
    void setUp() {
        feedbackType.setId(1L);
        feedbackType.setName(FeedbackTypeEnum.OFFER.toString());
    }

    @Test
    void getId() {
        assertEquals(1L, feedbackType.getId());
    }

    @Test
    void getName() {
        assertEquals(FeedbackTypeEnum.OFFER.toString(), feedbackType.getName());
    }

    @Test
    void setId() {
        feedbackType.setId(2L);
        assertEquals(2L, feedbackType.getId());
    }

    @Test
    void setName() {
        feedbackType.setName(FeedbackTypeEnum.BUG.toString());
        assertEquals(FeedbackTypeEnum.BUG.toString(), feedbackType.getName());
    }

    @Test
    void testToString() {
        assertNotNull(feedbackType.toString());
    }

    @Test
    void testEquals() {
        FeedbackType feedbackType1 = FeedbackType.builder()
                .id(1L)
                .name(FeedbackTypeEnum.OFFER.toString())
                .build();
        assertEquals(feedbackType1, feedbackType);
    }

}