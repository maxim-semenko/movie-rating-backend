package com.max.movierating.service.impl;

import com.max.movierating.entity.Role;
import com.max.movierating.entity.User;
import com.max.movierating.exception.BadRequestException;
import com.max.movierating.exception.ResourceNotFoundException;
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

    @Autowired
    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
                log.error("User not found!");
                throw new ResourceNotFoundException("User not found!");
            }
        } else {
            log.error("Can't update accountIsNonLocked for yourself!");
            throw new BadRequestException("Can't update accountIsNonLocked for yourself!");
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUserRoleById(Set<Role> roles, Long userId) {
        return null;
    }
}
