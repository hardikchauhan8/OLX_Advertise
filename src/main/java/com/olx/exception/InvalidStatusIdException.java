package com.olx.exception;

import com.olx.utils.ExceptionConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)

public class InvalidStatusIdException extends RuntimeException {

    private int status;

    public InvalidStatusIdException() {

    }

    public InvalidStatusIdException(int status) {
        super();
        this.status = status;
    }

    @Override
    public String toString() {
        return ExceptionConstants.INVALID_STATUS_ID + status;
    }
}
