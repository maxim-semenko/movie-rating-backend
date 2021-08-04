package com.max.movierating.service.impl;

import com.max.movierating.dto.AuthenticationRequestDto;
import com.max.movierating.dto.UserDto;
import com.max.movierating.entity.EnumRole;
import com.max.movierating.entity.User;
import com.max.movierating.exception.UserExistException;
import com.max.movierating.exception.UserNotFoundException;
import com.max.movierating.repository.RoleRepository;
import com.max.movierating.repository.UserRepository;
import com.max.movierating.security.JwtTokenProvider;
import com.max.movierating.service.DefaultService;
import com.max.movierating.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements DefaultService<User>, UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User id: " + id + " was not found"));
    }

    @Override
    public User save(User user) {
        checkUsernameAndEmail(user);

        user.setRoles(Set.of(roleRepository.findByName(EnumRole.ROLE_USER)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        checkUsernameAndEmail(user);
        return userRepository.save(user);
    }

    @Override
    public Long deleteById(Long id) {
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User id: " + id + " was not found"));
        userRepository.deleteById(id);
        return id;
    }

    /**
     * Method that checks login and email of user.
     *
     * @param user {@link User}
     */
    private void checkUsernameAndEmail(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserExistException("Login are existed");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserExistException("Email are existed");
        }
    }

    @Override
    public Map<String, Object> login(AuthenticationRequestDto requestDto) {
        String username = requestDto.getUsername();
        User user = findByUsername(username);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            Map<String, Object> response = new HashMap<>();
            response.put("user", UserDto.fromUser(user));
            response.put("token", jwtTokenProvider.createToken(username, user.getRoles()));

            return response;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid password");
        }

    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.warn("User not found with username: " + username);
            throw new UserNotFoundException("User not found with username = " + username);
        }
        return user;
    }

}
