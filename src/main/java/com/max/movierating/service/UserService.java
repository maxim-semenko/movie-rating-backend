package com.max.movierating.service;

import com.max.movierating.dto.other.UpdatePasswordDTO;
import com.max.movierating.entity.Role;
import com.max.movierating.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

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

    User updateUserIsNonLockedById(Boolean isNonLocked, Long id);

    User updateUserRolesById(Set<Role> roles, Long id);

}
