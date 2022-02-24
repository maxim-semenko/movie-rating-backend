package com.max.movierating.service;

import com.max.movierating.dto.entity.RequestFeedbackDTO;
import com.max.movierating.entity.Feedback;
import com.max.movierating.entity.FeedbackType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeedbackService {

    Page<Feedback> findAll(Pageable pageable);

    Feedback create(RequestFeedbackDTO createFeedbackDTO);

    Page<FeedbackType> findAllTypes(Pageable pageable);

}
