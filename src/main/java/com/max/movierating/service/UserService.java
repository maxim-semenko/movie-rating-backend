package com.max.movierating.service;

import com.max.movierating.dto.other.UpdatePasswordDTO;
import com.max.movierating.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * User's service interface.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
public interface UserService {

    User getByUsername(String username);

    User updatePasswordById(Long id, UpdatePasswordDTO updatePasswordDTO);

    void existByUsername(String username);

    void existByEmail(String email);

    Page<User> getAllByPages(Pageable pageable);

    Boolean deleteAccount(Long id, String password);

    User findByEmail(String email);

}