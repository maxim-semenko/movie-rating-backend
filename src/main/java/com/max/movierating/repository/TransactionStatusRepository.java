package com.max.movierating.repository;

import com.max.movierating.entity.Transaction;
import com.max.movierating.entity.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TransactionStatusRepository for working with entity {@link TransactionStatus}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Repository
public interface TransactionStatusRepository extends JpaRepository<TransactionStatus, Long> {

    TransactionStatus findTransactionStatusByName(String name);
}
