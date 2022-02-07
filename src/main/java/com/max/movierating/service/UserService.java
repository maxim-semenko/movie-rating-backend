package com.max.movierating.service;

import com.max.movierating.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User getByUsername(String username);

    User updatePasswordById(Long id, String oldPassword, String newPassword);

    User restorePassword(Long id, String oldPassword, String newPassword, Integer emailCode);

    void existByUsername(String username);

    void existByEmail(String email);

    Page<User> getAllByPages(Pageable pageable);

    Boolean deleteAccount(Long id, String password);

}