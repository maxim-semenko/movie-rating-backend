package com.max.movierating.repository;

import com.max.movierating.entity.Transaction;
import com.max.movierating.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TransactionRepository for working with entity {@link Transaction}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    /**
     * Method that finds all transactions by user.
     *
     * @param user params for search
     * @return list of transactions
     */
    List<Transaction> findAllByUser(User user);
}
