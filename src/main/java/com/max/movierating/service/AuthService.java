package com.max.movierating.service;

import com.max.movierating.dto.other.RequestLoginDTO;
import com.max.movierating.entity.User;

import java.util.Map;

public interface AuthService {

    User register(User user);

    Map<String, Object> login(RequestLoginDTO loginDTO);

    Boolean logout();

    Boolean restorePassword(Long id, String newPassword, Integer emailCode);

}
