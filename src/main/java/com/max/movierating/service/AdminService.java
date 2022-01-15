package com.max.movierating.service;

import com.max.movierating.entity.User;

public interface AdminService {

    User updateUserIsNonLockedById(Boolean isNonLocked, Long userId);

    User addOrRemoveAdminRoleById(Long userId);
}
