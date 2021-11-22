package com.olx.exception;

import com.olx.utils.ExceptionConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)

public class InvalidCategoryIdException extends RuntimeException {

    private int category;

    public InvalidCategoryIdException() {

    }

    public InvalidCategoryIdException(int category) {
        super();
        this.category = category;
    }

    @Override
    public String toString() {
        return ExceptionConstants.INVALID_CATEGORY_ID + this.category;
    }
}
