package com.max.movierating.service.impl;

import com.max.movierating.entity.Basket;
import com.max.movierating.entity.User;
import com.max.movierating.exception.BadRequestException;
import com.max.movierating.exception.ResourceNotFoundException;
import com.max.movierating.exception.UserExistException;
import com.max.movierating.repository.BasketRepository;
import com.max.movierating.repository.RoleRepository;
import com.max.movierating.repository.UserRepository;
import com.max.movierating.service.DefaultService;
import com.max.movierating.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements DefaultService<User, Long>, UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BasketRepository basketRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BasketRepository basketRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.basketRepository = basketRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id: " + id + " was not found"));
    }

    @Override
    @Transactional(rollbackFor = ConstraintViolationException.class)
    public User save(User user) {
        existByUsername(user.getUsername());
        existByEmail(user.getEmail());

        Basket basket = new Basket();
        basketRepository.save(basket);

        user.setRoles(Set.of(roleRepository.findByName("ROLE_USER")));
        user.setIsAccountNonLocked(Boolean.TRUE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setBasket(basket);
        user.setId(basket.getId());

        return userRepository.save(user);
    }

    @Override
    public User update(User user, Long id) {
        User existUser = findById(id);

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

        return userRepository.save(user);
    }

    @Override
    public User deleteById(Long id) {
        User user = findById(id);
        userRepository.delete(user);
        return user;
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
        if (existUser != null) {
            if (passwordEncoder.matches(oldPassword, existUser.getPassword())) {
                existUser.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(existUser);
            } else {
                log.error("Invalid old Password!");
                throw new BadRequestException("Invalid old Password!");
            }
        }
        return existUser;
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

    @Override
    public Boolean deleteAccount(Long id, String password) {
        User existUser = findById(id);
        if (existUser != null) {
            if (passwordEncoder.matches(password, existUser.getPassword())) {
                userRepository.delete(existUser);
                SecurityContextHolder.getContext().setAuthentication(null);
                SecurityContextHolder.clearContext();
                log.info("User have deleted successfully!");
            } else {
                log.error("Invalid old Password!");
                throw new BadRequestException("Invalid old Password!");
            }
        }
        return true;
    }

}
