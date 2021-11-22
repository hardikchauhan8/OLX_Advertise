package com.olx.exception;

import com.olx.utils.ExceptionConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)

public class InvalidAdvertiseIdException extends RuntimeException {

    private int adId;

    public InvalidAdvertiseIdException() {

    }

    public InvalidAdvertiseIdException(int adId) {
        super();
        this.adId = adId;
    }

    @Override
    public String toString() {
        return ExceptionConstants.INVALID_ADVERTISE_ID + this.adId;
    }
}
