package com.max.movierating.service.impl;

import com.max.movierating.entity.PurchaseStorage;
import com.max.movierating.entity.User;
import com.max.movierating.exception.ResourceNotFoundException;
import com.max.movierating.repository.UserRepository;
import com.max.movierating.service.PurchaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseStorageServiceImpl implements PurchaseStorageService {

    private final UserRepository userRepository;

    @Autowired
    public PurchaseStorageServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public PurchaseStorage findByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        PurchaseStorage purchaseStorage;
        if (optionalUser.isPresent()) {
            purchaseStorage = optionalUser.get().getPurchaseStorage();
        } else {
            throw new ResourceNotFoundException("");
        }

        return purchaseStorage;
    }
}
