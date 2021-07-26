package com.max.movierating.service;

import com.max.movierating.dto.AuthenticationRequestDto;
import com.max.movierating.entity.User;

import java.util.Map;

public interface UserService {

    Map<String, Object> login(AuthenticationRequestDto requestDto);

    User findByUsername(String username);


}
