package com.max.movierating.dto;

import com.max.movierating.entity.Role;
import com.max.movierating.entity.User;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private Integer balance;
    private Set<Role> roles = new HashSet<>();

    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setBalance(balance);
        user.setRoles(roles);

        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setEmail(user.getEmail());
        userDto.setBalance(user.getBalance());
        userDto.setRoles(user.getRoles());

        return userDto;
    }
}
