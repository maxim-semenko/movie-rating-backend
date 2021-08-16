package com.max.movierating.service;

import com.max.movierating.dto.AuthenticationRequestDTO;
import com.max.movierating.entity.User;

import java.util.Map;

public interface UserService {

    Map<String, Object> login(AuthenticationRequestDTO requestDto);

    User getByUsername(String username);


}
