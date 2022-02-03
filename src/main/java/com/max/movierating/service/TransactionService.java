package com.max.movierating.service;

import com.max.movierating.entity.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> findAllByUserId(Long userId);
}
