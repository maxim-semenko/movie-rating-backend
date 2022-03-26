package com.max.movierating.service.impl;

import com.max.movierating.entity.PurchaseStorage;
import com.max.movierating.entity.User;
import com.max.movierating.service.PurchaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Role Service implementation that realize PurchaseStorageService interface {@link PurchaseStorageService}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Service
public class PurchaseStorageServiceImpl implements PurchaseStorageService {

    private final UserServiceImpl userService;

    @Autowired
    public PurchaseStorageServiceImpl(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public PurchaseStorage findByUserId(Long userId) {
        User user = userService.findById(userId);
        return user.getPurchaseStorage();
    }
}
