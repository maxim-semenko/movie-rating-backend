package com.max.movierating.service;

import com.max.movierating.entity.Film;
import com.max.movierating.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User getByUsername(String username);

    void existByUsername(String username);

    void existByEmail(String email);

    Page<User> getAllByPages(Pageable pageable);

}