package com.max.movierating.controller;

import com.max.movierating.constant.APIConstant;
import com.max.movierating.dto.entity.RequestFeedbackDTO;
import com.max.movierating.entity.Feedback;
import com.max.movierating.entity.FeedbackType;
import com.max.movierating.service.impl.FeedbackServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * REST controller for feedback requests.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@RestController
@RequestMapping(value = APIConstant.FEEDBACK_API)
public class FeedbackController {

    private final FeedbackServiceImpl feedbackService;

    @Autowired
    public FeedbackController(FeedbackServiceImpl feedbackService) {
        this.feedbackService = feedbackService;
    }

    /**
     * Method that returns all feedbacks {@link Feedback}.
     *
     * @return feedbacks {@link Feedback}
     */
    @GetMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Feedback>> findAll(Pageable pageable) {
        return new ResponseEntity<>(feedbackService.findAll(pageable), HttpStatus.OK);
    }

    /**
     * Method that returns all feedback's types {@link FeedbackType}.
     *
     * @return feedback's types {@link FeedbackType}
     */
    @GetMapping("/types")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Page<FeedbackType>> findAllTypes(Pageable pageable) {
        return new ResponseEntity<>(feedbackService.findAllTypes(pageable), HttpStatus.OK);
    }

    /**
     * Method that create a new feedback {@link Feedback}.
     *
     * @return saved feedback {@link Feedback}
     */
    @PostMapping("")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Feedback> create(@Valid @RequestBody RequestFeedbackDTO request) {
        return new ResponseEntity<>(feedbackService.create(request), HttpStatus.CREATED);
    }

}
