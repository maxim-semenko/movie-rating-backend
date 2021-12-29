package com.max.movierating.service.impl;

import com.max.movierating.dto.RequestLoginDTO;
import com.max.movierating.dto.UserDTO;
import com.max.movierating.entity.User;
import com.max.movierating.exception.BadRequestException;
import com.max.movierating.exception.ResourceNotFoundException;
import com.max.movierating.repository.UserRepository;
import com.max.movierating.security.JwtTokenProvider;
import com.max.movierating.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           AuthenticationManager authenticationManager,
                           JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Method that login user in system.
     *
     * @param loginDTO contain username and password {@link RequestLoginDTO}
     * @return user date and token
     */
    @Override
    public Map<String, Object> login(RequestLoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        User user = userRepository.findByUsername(username);

        if (user != null) {
            if (user.getIsAccountNonLocked()) {
                try {
                    log.info("try user login!");
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, loginDTO.getPassword()));
                    Map<String, Object> response = new HashMap<>();
                    response.put("user", UserDTO.fromUser(user));
                    response.put("token", jwtTokenProvider.createToken(user.getUsername(), user.getRoles()));
                    log.info("User login is successful!");

                    return response;
                } catch (AuthenticationException e) {
                    log.error("User login is error!");
                    throw new BadCredentialsException("Invalid password!");
                }
            } else {
                log.error("User locked!");
                throw new BadRequestException("User locked!");
            }
        } else {
            throw new ResourceNotFoundException("User was not found!");
        }
    }

    @Override
    public Boolean logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        SecurityContextHolder.clearContext();
        log.info("User logout is successful");
        return true;
    }

    @Override
    public String generateNewToken(String username) {
        User user = userRepository.findByUsername(username);
        String token;
        if (user != null) {
            token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        } else {
            throw new ResourceNotFoundException("User not found!");
        }
        return token;
    }

}
