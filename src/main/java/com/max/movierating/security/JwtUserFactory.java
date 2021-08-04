package com.max.movierating.security;

import com.max.movierating.entity.Role;
import com.max.movierating.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(new HashSet<>(user.getRoles()))
        );
    }

    private static Set<GrantedAuthority> mapToGrantedAuthorities(Set<Role> userRoles) {
        return userRoles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                .collect(Collectors.toSet());
    }
}

