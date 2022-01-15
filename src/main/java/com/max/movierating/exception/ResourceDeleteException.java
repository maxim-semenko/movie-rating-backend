package com.max.movierating.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceDeleteException extends RuntimeException {
    public ResourceDeleteException(String message) {
        super(message);
    }
}
