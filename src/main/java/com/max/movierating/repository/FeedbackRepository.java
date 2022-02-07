package com.max.movierating.repository;

import com.max.movierating.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * FeedbackRepository for working with entity {@link Feedback}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
