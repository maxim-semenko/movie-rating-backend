package com.max.movierating.controller;

import com.max.movierating.dto.LoginRequestDTO;
import com.max.movierating.entity.User;
import com.max.movierating.service.impl.AuthServiceImpl;
import com.max.movierating.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * REST controller for authentication requests (login, register, generate token).
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
     * Authentication service for working with user's login, register, etc.
     */
    private final AuthServiceImpl authService;

    /**
     * Method that is responsible for login of user.
     *
     * @param requestDto contain username and password
     * @return user
     */
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO requestDto) {
        return new ResponseEntity<>(authService.login(requestDto), HttpStatus.OK);
    }

    /**
     * Method that is responsible for register of user.
     *
     * @param user new user
     * @return user
     */
    @PostMapping("register")
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    /**
     * Method that generate new token for user.
     *
     * @param user user
     * @return token
     */
    @PostMapping("token")
    public ResponseEntity<String> generateNewToken(@RequestBody User user) {
        return new ResponseEntity<>(authService.generateNewToken(user), HttpStatus.OK);
    }

}
