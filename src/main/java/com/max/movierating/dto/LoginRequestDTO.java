package com.max.movierating.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * DTO class for authentication (login) request.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Data
public class LoginRequestDTO {

    @NotEmpty(message = "Please provide a username")
    private String username;

    @NotEmpty(message = "Please provide a password")
    private String password;
}