package com.max.movierating.service.impl;

import com.max.movierating.dto.RequestLoginDTO;
import com.max.movierating.dto.RequestRegisterDTO;
import com.max.movierating.dto.UserDTO;
import com.max.movierating.entity.User;
import com.max.movierating.repository.UserRepository;
import com.max.movierating.security.JwtTokenProvider;
import com.max.movierating.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Map<String, Object> login(RequestLoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        User user = userRepository.findByUsername(username);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, loginDTO.getPassword()));
            Map<String, Object> response = new HashMap<>();
            response.put("user", UserDTO.fromUser(user));
            response.put("token", generateNewToken(user));

            return response;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid password");
        }

    }

    @Override
    public Boolean register(RequestRegisterDTO registerDTO) {
        return true;
    }

    @Override
    public String generateNewToken(User user) {
        return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
    }

}
