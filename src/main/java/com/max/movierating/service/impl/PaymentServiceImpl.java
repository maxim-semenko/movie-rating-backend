package com.max.movierating.service.impl;

import com.max.movierating.dto.RequestPaymentDTO;
import com.max.movierating.entity.User;
import com.max.movierating.repository.UserRepository;
import com.max.movierating.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final UserRepository userRepository;

    @Autowired
    public PaymentServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean pay(RequestPaymentDTO requestPaymentDTO) {
        Optional<User> userOptional = userRepository.findById(requestPaymentDTO.getUserId());

        return true;
    }
}
