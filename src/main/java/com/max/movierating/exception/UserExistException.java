package com.max.movierating.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserExistException extends RuntimeException {
    public UserExistException(String message) {
        super(message);
    }
}
