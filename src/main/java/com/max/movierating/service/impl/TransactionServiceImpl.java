package com.max.movierating.service.impl;

import com.max.movierating.entity.Transaction;
import com.max.movierating.entity.User;
import com.max.movierating.repository.TransactionRepository;
import com.max.movierating.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Transaction Service implementation that realize TransactionService interface {@link TransactionService}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    /**
     * TransactionRepository for working with transactions {@link Transaction}.
     */
    private final TransactionRepository transactionRepository;
    /**
     * UserService for working with user {@link User}.
     */
    private final UserServiceImpl userService;

    /**
     * Constructor
     *
     * @param transactionRepository for working with transactions {@link Transaction}.
     * @param userService           for working with user {@link User}
     */
    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  UserServiceImpl userService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
    }

    /**
     * Method that returns all transactions by userId.
     *
     * @param userId params for search
     * @return list of transactions
     */
    @Override
    public List<Transaction> findAllByUserId(Long userId) {
        User user = userService.findById(userId);
        return transactionRepository.findAllByUser(user);
    }
}
