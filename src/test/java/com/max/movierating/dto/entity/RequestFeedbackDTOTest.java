package com.max.movierating.dto.entity;

import com.max.movierating.entity.Feedback;
import com.max.movierating.entity.FeedbackType;
import com.max.movierating.entity.enums.FeedbackTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestFeedbackDTOTest {

    private final RequestFeedbackDTO requestFeedbackDTO = new RequestFeedbackDTO();

    @BeforeEach
    void setUp() {
        requestFeedbackDTO.setTitle("Test1");
        requestFeedbackDTO.setText("Test2");
        requestFeedbackDTO.setFeedbackType(new FeedbackType(1L, FeedbackTypeEnum.COMMENT.toString()));
    }

    @Test
    void toFeedback() {
        Feedback feedback = new Feedback();
        feedback.setTitle("Test1");
        feedback.setText("Test2");
        feedback.setFeedbackType(new FeedbackType(1L, FeedbackTypeEnum.COMMENT.toString()));
        assertEquals(feedback, requestFeedbackDTO.toFeedback());
    }

    @Test
    void getTitle() {
        assertEquals("Test1", requestFeedbackDTO.getTitle());
    }

    @Test
    void getText() {
        assertEquals("Test2", requestFeedbackDTO.getText());
    }

    @Test
    void getFeedbackType() {
        FeedbackType feedbackType = new FeedbackType(1L, FeedbackTypeEnum.COMMENT.toString());
        assertEquals(feedbackType, requestFeedbackDTO.getFeedbackType());

    }

    @Test
    void setTitle() {
        requestFeedbackDTO.setTitle("TEST1");
        assertEquals("TEST1", requestFeedbackDTO.getTitle());
    }

    @Test
    void setText() {
        requestFeedbackDTO.setText("TEST2");
        assertEquals("TEST2", requestFeedbackDTO.getText());
    }

    @Test
    void setFeedbackType() {
        FeedbackType feedbackType = new FeedbackType(2L, FeedbackTypeEnum.BUG.toString());
        requestFeedbackDTO.setFeedbackType(feedbackType);
        assertEquals(feedbackType, requestFeedbackDTO.getFeedbackType());
    }
}