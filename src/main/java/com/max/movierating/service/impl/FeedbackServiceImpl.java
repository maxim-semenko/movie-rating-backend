package com.max.movierating.service.impl;

import com.max.movierating.dto.entity.RequestFeedbackDTO;
import com.max.movierating.entity.Feedback;
import com.max.movierating.entity.FeedbackType;
import com.max.movierating.repository.FeedbackRepository;
import com.max.movierating.repository.FeedbackTypeRepository;
import com.max.movierating.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackTypeRepository feedbackTypeRepository;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepository feedbackRepository,
                               FeedbackTypeRepository feedbackTypeRepository) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackTypeRepository = feedbackTypeRepository;
    }

    @Override
    public Page<Feedback> findAll(Pageable pageable) {
        return feedbackRepository.findAll(pageable);
    }

    @Override
    public Feedback create(RequestFeedbackDTO createFeedbackDTO) {
        return feedbackRepository.save(createFeedbackDTO.toFeedback());
    }

    @Override
    public Page<FeedbackType> findAllTypes(Pageable pageable) {
        return feedbackTypeRepository.findAll(pageable);
    }
}
