package com.max.movierating.service.impl;

import com.max.movierating.entity.Transaction;
import com.max.movierating.entity.User;
import com.max.movierating.repository.TransactionRepository;
import com.max.movierating.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserServiceImpl userService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  UserServiceImpl userService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    @Override
    public List<Transaction> findAllByUserId(Long userId) {
        User user = userService.findById(userId);
        return transactionRepository.findAllByUser(user);
    }
}
