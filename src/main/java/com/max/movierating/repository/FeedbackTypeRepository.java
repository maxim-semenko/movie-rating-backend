package com.max.movierating.repository;

import com.max.movierating.entity.FeedbackType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * FeedbackTypeRepository for working with entity {@link FeedbackType}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Repository
public interface FeedbackTypeRepository extends JpaRepository<FeedbackType, Long> {
    /**
     * Method that finds all feedbackType by pageable.
     *
     * @param pageable contain any params (size, page, etc)
     * @return page of feedbackType
     */
    Page<FeedbackType> findAll(Pageable pageable);
}
