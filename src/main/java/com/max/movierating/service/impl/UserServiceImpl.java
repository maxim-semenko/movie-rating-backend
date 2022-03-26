package com.max.movierating.service.impl;

import com.max.movierating.constant.ErrorConstant;
import com.max.movierating.dto.other.UpdatePasswordDTO;
import com.max.movierating.dto.other.UserDTO;
import com.max.movierating.entity.Mark;
import com.max.movierating.entity.Role;
import com.max.movierating.entity.Transaction;
import com.max.movierating.entity.User;
import com.max.movierating.exception.BadRequestException;
import com.max.movierating.exception.ResourceNotFoundException;
import com.max.movierating.exception.UserExistException;
import com.max.movierating.repository.MailCodeRepository;
import com.max.movierating.repository.MarkRepository;
import com.max.movierating.repository.TransactionRepository;
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
import java.util.Set;

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
    private final TransactionRepository transactionRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           MarkRepository markRepository,
                           MailCodeRepository mailCodeRepository,
                           TransactionRepository transactionRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.markRepository = markRepository;
        this.mailCodeRepository = mailCodeRepository;
        this.transactionRepository = transactionRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " was not found"));
    }

    public Map<String, Object> update(User user, Long id) {
        User existUser = findById(id);
        Map<String, Object> response = new HashMap<>();

        if (!user.getUsername().equals(existUser.getUsername())) {
            existByUsername(user.getUsername());
        }
        if (!user.getEmail().equals(existUser.getEmail())) {
            existByEmail(user.getEmail());
        }

        existUser.setFirstname(user.getFirstname());
        existUser.setLastname(user.getLastname());
        existUser.setEmail(user.getEmail());
        existUser.setUsername(user.getUsername());

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
    public User updatePasswordById(Long id, UpdatePasswordDTO updatePasswordDTO) {
        String oldPassword = updatePasswordDTO.getOldPassword();
        String newPassword = updatePasswordDTO.getNewPassword();

        User existUser = findById(id);

        if (passwordEncoder.matches(oldPassword, existUser.getPassword())) {
            existUser.setPassword(passwordEncoder.encode(newPassword));
        } else {
            log.error(ErrorConstant.ERROR_INVALID_OLD_PASSWORD);
            throw new BadRequestException(ErrorConstant.ERROR_INVALID_OLD_PASSWORD);
        }

        return userRepository.save(existUser);
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

        if (passwordEncoder.matches(password, existUser.getPassword())) {
            mailCodeRepository.deleteAllByUser(existUser);
            List<Mark> markList = markRepository.findAllByUser(existUser);
            for (Mark mark : markList) {
                mark.setUser(null);
            }

            List<Transaction> transactionalList = transactionRepository.findAllByUser(existUser);
            for (Transaction transaction : transactionalList) {
                transaction.setUser(null);
            }
            userRepository.delete(existUser);

            SecurityContextHolder.getContext().setAuthentication(null);
            SecurityContextHolder.clearContext();
            log.info("User have deleted successfully!");
        } else {
            log.error(ErrorConstant.ERROR_INVALID_OLD_PASSWORD);
            throw new BadRequestException(ErrorConstant.ERROR_INVALID_OLD_PASSWORD);
        }

        return true;
    }

    @Override
    public User findByEmail(String email) {
        User user;
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            log.error("User was not found with email: " + email);
            throw new ResourceNotFoundException("User not found with email: " + email);
        }

        return user;
    }

    @Override
    public User updateUserIsNonLockedById(Boolean isNonLocked, Long userId) {
        User user = findById(userId);
        user.setIsAccountNonLocked(isNonLocked);
        return userRepository.save(user);
    }

    @Override
    public User updateUserRolesById(Set<Role> roles, Long id) {
        User user = findById(id);
        user.setRoles(roles);
        return userRepository.save(user);
    }

}
