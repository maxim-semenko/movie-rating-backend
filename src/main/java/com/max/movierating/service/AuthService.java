package com.max.movierating.service;

import com.max.movierating.dto.LoginRequestDTO;
import com.max.movierating.entity.User;

import java.util.Map;

public interface AuthService {

    Map<String, Object> login(LoginRequestDTO requestDto);

    String generateNewToken(User user);
}
