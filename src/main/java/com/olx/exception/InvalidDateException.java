package com.olx.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)

public class InvalidDateException extends RuntimeException {

    private String message;

    public InvalidDateException() {

    }

    public InvalidDateException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
