package com.max.movierating.service;

import com.max.movierating.entity.Role;
import com.max.movierating.entity.User;

import java.util.Set;

public interface AdminService {

    User updateUserIsNonLockedById(Boolean isNonLocked, Long userId);

    User updateUserRoleById(Set<Role> roles, Long userId);
}
