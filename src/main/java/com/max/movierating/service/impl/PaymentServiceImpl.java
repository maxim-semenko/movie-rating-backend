package com.max.movierating.service.impl;

import com.max.movierating.repository.UserRepository;
import com.max.movierating.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private final UserRepository userRepository;
//    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public PaymentServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
//        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Boolean pay() {
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String username = userDetails.getUsername();

//        User user = userRepository.findByUsername(username);
//        log.info("USERNAME: " + username);


//        String username1 = jwtTokenProvider.getUsername(token);
//        log.info("USERNAME1: " + username1);

        return true;
    }
}
