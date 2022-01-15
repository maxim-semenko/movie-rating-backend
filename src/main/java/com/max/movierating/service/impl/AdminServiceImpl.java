package com.max.movierating.service.impl;

import com.max.movierating.constant.ErrorConstant;
import com.max.movierating.constant.RoleConstant;
import com.max.movierating.entity.Role;
import com.max.movierating.entity.User;
import com.max.movierating.exception.BadRequestException;
import com.max.movierating.exception.ResourceNotFoundException;
import com.max.movierating.repository.RoleRepository;
import com.max.movierating.repository.UserRepository;
import com.max.movierating.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User updateUserIsNonLockedById(Boolean isNonLocked, Long userId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUserInSystem = userRepository.findByUsername(userDetails.getUsername());
        User user;
        if (!Objects.equals(userId, currentUserInSystem.getId())) {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                user = userOptional.get();
                user.setIsAccountNonLocked(isNonLocked);
            } else {
                log.error(ErrorConstant.USER_NOT_FOUND);
                throw new ResourceNotFoundException(ErrorConstant.USER_NOT_FOUND);
            }
        } else {
            log.error("Can't update accountIsNonLocked for yourself!");
            throw new BadRequestException("Can't update accountIsNonLocked for yourself!");
        }
        return userRepository.save(user);
    }

    @Override
    public User addOrRemoveAdminRoleById(Long userId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUserInSystem = userRepository.findByUsername(userDetails.getUsername());
        User user;
        if (!Objects.equals(userId, currentUserInSystem.getId())) {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                user = userOptional.get();
                Set<Role> userRoles = user.getRoles();
                Role adminRole = roleRepository.findByName(RoleConstant.ADMIN);
                if (userRoles.contains(adminRole)) {
                    userRoles.remove(adminRole);
                } else {
                    userRoles.add(adminRole);
                }
            } else {
                log.error(ErrorConstant.USER_NOT_FOUND);
                throw new ResourceNotFoundException(ErrorConstant.USER_NOT_FOUND);
            }
        } else {
            log.error("Can't update accountIsNonLocked for yourself!");
            throw new BadRequestException("Can't update accountIsNonLocked for yourself!");
        }
        return userRepository.save(user);
    }
}
