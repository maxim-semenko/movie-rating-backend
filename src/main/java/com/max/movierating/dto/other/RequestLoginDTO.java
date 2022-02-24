package com.max.movierating.dto.other;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * DTO class for authentication (login) request.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Getter
@Setter
public class RequestLoginDTO {

    @NotBlank(message = "Username may not be empty")
    private String username;

    @NotBlank(message = "Password may not be empty")
    private String password;

}