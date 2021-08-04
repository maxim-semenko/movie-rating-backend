package com.max.movierating.controller;

import com.max.movierating.dto.AuthenticationRequestDto;
import com.max.movierating.entity.User;
import com.max.movierating.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for authentication requests (login, register).
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/v1/auth/")
@RequiredArgsConstructor
public class AuthenticationController {

    /**
     * User service for working with {@link User}.
     */
    private final UserServiceImpl userService;

    /**
     * Method that is responsible for login of user.
     *
     * @param requestDto contain username and password
     * @return user or error 403, 404
     */
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto requestDto) {
        return new ResponseEntity<>(userService.login(requestDto), HttpStatus.OK);
    }

    /**
     * Method that is responsible for register of user.
     *
     * @param user new user
     * @return user
     */
    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

}