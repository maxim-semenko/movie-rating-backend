package com.max.movierating.dto;

import lombok.Data;

/**
 * DTO class for authentication (login) request.
 *
 * @author Maxim Semenko
 * @version 1.0
 */
@Data
public class LoginRequestDTO {
    private String username;
    private String password;
}