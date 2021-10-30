package com.max.movierating.dto;

import com.max.movierating.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
public class RequestRegisterDTO {

    @NotEmpty(message = "Please provide a username")
    private String username;

    @NotEmpty(message = "Please provide a password")
    private String password;

    @NotEmpty(message = "Please provide a firstname")
    private String firstname;

    @NotEmpty(message = "Please provide a lastname")
    private String lastname;

    @NotEmpty(message = "Please provide a email")
    private String email;

    public User toUser() {
        return User.builder()
                .username(username)
                .password(password)
                .firstname(firstname)
                .lastname(lastname)
                .email(email)
                .build();
    }

}
