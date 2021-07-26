package com.max.movierating.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FilmNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FilmNotFoundException(String message) {
        super(message);
    }
}
