package com.max.movierating.service.impl;

import com.max.movierating.constant.ErrorConstant;
import com.max.movierating.dto.UserDTO;
import com.max.movierating.entity.User;
import com.max.movierating.exception.BadRequestException;
import com.max.movierating.exception.ResourceNotFoundException;
import com.max.movierating.exception.UserExistException;
import com.max.movierating.repository.MailCodeRepository;
import com.max.movierating.repository.MarkRepository;
import com.max.movierating.repository.UserRepository;
import com.max.movierating.security.JwtTokenProvider;
import com.max.movierating.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * User Service implementation that realize UserService interface {@link UserService}.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MarkRepository markRepository;
    private final MailCodeRepository mailCodeRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           MarkRepository markRepository,
                           MailCodeRepository mailCodeRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.markRepository = markRepository;
        this.mailCodeRepository = mailCodeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User with id: " + id + " was not found");
        }

        return userOptional.get();
    }

    public Map<String, Object> update(User user, Long id) {
        User existUser = findById(id);
        Map<String, Object> response = new HashMap<>();

        user.setId(id);
        user.setPassword(existUser.getPassword());
        user.setIsAccountNonLocked(existUser.getIsAccountNonLocked());
        user.setRoles(existUser.getRoles());
        user.setBasket(existUser.getBasket());

        if (!user.getUsername().equals(existUser.getUsername())) {
            existByUsername(user.getUsername());
        }
        if (!user.getEmail().equals(existUser.getEmail())) {
            existByEmail(user.getEmail());
        }

        userRepository.save(user);
        response.put("user", UserDTO.fromUser(user));
        response.put("token", jwtTokenProvider.createToken(user.getUsername(), user.getRoles()));

        return response;
    }


    @Override
    public User getByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User was not found with username: " + username);
            throw new ResourceNotFoundException("User not found with username = " + username);
        }
        return user;
    }

    @Override
    public User updatePasswordById(Long id, String oldPassword, String newPassword) {
        User existUser = findById(id);
        if (passwordEncoder.matches(oldPassword, existUser.getPassword())) {
            existUser.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(existUser);
        } else {
            log.error(ErrorConstant.ERROR_INVALID_OLD_PASSWORD);
            throw new BadRequestException(ErrorConstant.ERROR_INVALID_OLD_PASSWORD);
        }

        return existUser;
    }

    @Override
    public User restorePassword(Long id, String oldPassword, String newPassword, Integer emailCode) {
        return null;
    }

    @Override
    public void existByUsername(String username) {
        if (Boolean.TRUE.equals(userRepository.existsByUsername(username))) {
            throw new UserExistException("Username: " + username + " are existed already!");
        }
    }

    @Override
    public void existByEmail(String email) {
        if (Boolean.TRUE.equals(userRepository.existsByEmail(email))) {
            throw new UserExistException("Email: " + email + " are existed already!");
        }
    }

    @Override
    public Page<User> getAllByPages(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Boolean deleteAccount(Long id, String password) {
        User existUser = findById(id);
        if (existUser != null) {
            if (passwordEncoder.matches(password, existUser.getPassword())) {
                mailCodeRepository.deleteAllByUser(existUser);
                markRepository.deleteAllByUser(existUser);
                userRepository.delete(existUser);
                SecurityContextHolder.getContext().setAuthentication(null);
                SecurityContextHolder.clearContext();
                log.info("User have deleted successfully!");
            } else {
                log.error(ErrorConstant.ERROR_INVALID_OLD_PASSWORD);
                throw new BadRequestException(ErrorConstant.ERROR_INVALID_OLD_PASSWORD);
            }
        }
        return true;
    }

}
