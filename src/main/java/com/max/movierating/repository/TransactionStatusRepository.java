package com.max.movierating.repository;

import com.max.movierating.entity.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionStatusRepository extends JpaRepository<TransactionStatus, Long> {

    TransactionStatus findTransactionStatusByName(String name);
}
