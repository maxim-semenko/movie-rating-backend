package com.max.movierating.service;

import com.max.movierating.dto.RequestLoginDTO;
import com.max.movierating.entity.User;

import java.util.Map;

public interface AuthService {

    Map<String, Object> login(RequestLoginDTO loginDTO);

    Boolean logout();

    String generateNewToken(User user);
}
