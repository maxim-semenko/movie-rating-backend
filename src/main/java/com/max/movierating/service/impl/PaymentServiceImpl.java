package com.max.movierating.service.impl;

import com.max.movierating.repository.UserRepository;
import com.max.movierating.security.JwtTokenProvider;
import com.max.movierating.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Autowired
    public PaymentServiceImpl(JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @Override
    public Boolean pay(String token) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        System.out.println("USERNAME: " + username);
//        System.out.println(userRepository.);


//        String username = jwtTokenProvider.getUsername(token);
//        System.out.println("USERNAME: " + username);
        return true;
    }
}
