package com.max.movierating.dto.other;

import com.max.movierating.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RequestRegisterDTO {

    @NotBlank(message = "Please provide a username")
    private String username;

    @NotBlank(message = "Please provide a password")
    private String password;

    @NotBlank(message = "Please provide a firstname")
    private String firstname;

    @NotBlank(message = "Please provide a lastname")
    private String lastname;

    @NotBlank(message = "Please provide a email")
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
