package com.max.movierating.service.impl;

import com.max.movierating.entity.Role;
import com.max.movierating.entity.User;
import com.max.movierating.entity.enums.RoleEnum;
import com.max.movierating.repository.RoleRepository;
import com.max.movierating.repository.UserRepository;
import com.max.movierating.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Admin Service implementation that realize AdminService interface {@link AdminService}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserServiceImpl userService;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository,
                            RoleRepository roleRepository,
                            UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @Override
    public User updateUserIsNonLockedById(Boolean isNonLocked, Long userId) {
        User user = userService.findById(userId);
        user.setIsAccountNonLocked(isNonLocked);
        return userRepository.save(user);
    }

    @Override
    public User addOrRemoveAdminRoleById(Long userId) {
        User user = userService.findById(userId);

        Set<Role> userRoles = user.getRoles();
        Role adminRole = roleRepository.findByName(RoleEnum.ROLE_ADMIN.toString());
        if (userRoles.contains(adminRole)) {
            userRoles.remove(adminRole);
        } else {
            userRoles.add(adminRole);
        }

        return userRepository.save(user);
    }
}
