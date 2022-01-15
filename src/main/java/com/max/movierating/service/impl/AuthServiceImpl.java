package com.max.movierating.service.impl;

import com.max.movierating.constant.ErrorConstant;
import com.max.movierating.constant.RoleConstant;
import com.max.movierating.dto.RequestLoginDTO;
import com.max.movierating.dto.UserDTO;
import com.max.movierating.entity.Basket;
import com.max.movierating.entity.User;
import com.max.movierating.exception.BadRequestException;
import com.max.movierating.exception.ResourceNotFoundException;
import com.max.movierating.exception.UserExistException;
import com.max.movierating.repository.BasketRepository;
import com.max.movierating.repository.RoleRepository;
import com.max.movierating.repository.UserRepository;
import com.max.movierating.security.JwtTokenProvider;
import com.max.movierating.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BasketRepository basketRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BasketRepository basketRepository,
                           AuthenticationManager authenticationManager,
                           JwtTokenProvider jwtTokenProvider, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.basketRepository = basketRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(rollbackFor = ConstraintViolationException.class)
    public User register(User user) {
        if (Boolean.TRUE.equals(userRepository.existsByUsername(user.getUsername()))) {
            log.error(ErrorConstant.USERNAME_ALREADY_EXISTS + user.getUsername());
            throw new UserExistException(ErrorConstant.USERNAME_ALREADY_EXISTS + user.getUsername());
        }
        if (Boolean.TRUE.equals(userRepository.existsByEmail(user.getEmail()))) {
            log.error(ErrorConstant.EMAIL_ALREADY_EXISTS + user.getEmail());
            throw new UserExistException(ErrorConstant.EMAIL_ALREADY_EXISTS + user.getEmail());
        }

        Basket basket = new Basket();
        basketRepository.save(basket);

        user.setRoles(Set.of(roleRepository.findByName(RoleConstant.USER)));
        user.setIsAccountNonLocked(Boolean.TRUE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setBasket(basket);

        return userRepository.save(user);
    }


    /**
     * Method that login user in system.
     *
     * @param loginDTO contain username and password {@link RequestLoginDTO}
     * @return user date and token
     */
    @Override
    public Map<String, Object> login(RequestLoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        User user = userRepository.findByUsername(username);
        if (user != null) {
            if (user.getIsAccountNonLocked()) {
                try {
                    log.info("try user login!");
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, loginDTO.getPassword()));
                    Map<String, Object> response = new HashMap<>();
                    response.put("user", UserDTO.fromUser(user));
                    response.put("token", jwtTokenProvider.createToken(user.getUsername(), user.getRoles()));
                    log.info("User login is successful!");

                    return response;
                } catch (AuthenticationException e) {
                    log.error(ErrorConstant.ERROR_INVALID_PASSWORD);
                    throw new BadCredentialsException(ErrorConstant.ERROR_INVALID_PASSWORD);
                }
            } else {
                log.error("User locked!");
                throw new BadRequestException("User locked!");
            }
        } else {
            log.error("User was not found!");
            throw new ResourceNotFoundException("User was not found!");
        }
    }

    @Override
    public Boolean logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        SecurityContextHolder.clearContext();
        log.info("User logout is successful");
        return true;
    }


}
