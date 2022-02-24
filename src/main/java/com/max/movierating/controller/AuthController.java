package com.max.movierating.controller;

import com.max.movierating.constant.APIConstant;
import com.max.movierating.dto.other.RequestLoginDTO;
import com.max.movierating.dto.other.RequestRegisterDTO;
import com.max.movierating.dto.other.UserDTO;
import com.max.movierating.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * REST controller for authentication requests (register, login, logout).
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@RestController
@RequestMapping(value = APIConstant.AUTHENTICATION_API)
public class AuthController {

    /**
     * Authentication service for working with user's login, register, etc.
     */
    private final AuthServiceImpl authService;

    @Autowired
    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    /**
     * Method that is responsible for register of user.
     *
     * @param requestDTO register request
     * @return register user {@link UserDTO}
     */
    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody RequestRegisterDTO requestDTO) {
        return new ResponseEntity<>(UserDTO.fromUser(authService.register(requestDTO.toUser())), HttpStatus.CREATED);
    }

    /**
     * Method that is responsible for login of user.
     *
     * @param requestDto request contain username and password
     * @return user and token
     */
    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody RequestLoginDTO requestDto) {
        return new ResponseEntity<>(authService.login(requestDto), HttpStatus.OK);
    }

    /**
     * Method that logout user from system.
     *
     * @return boolean
     */
    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout() {
        return new ResponseEntity<>(authService.logout(), HttpStatus.OK);
    }

}
