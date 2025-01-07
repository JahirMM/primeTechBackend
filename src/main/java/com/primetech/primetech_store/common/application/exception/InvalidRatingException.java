package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.BadRequestException;

public class InvalidRatingException extends BadRequestException {
    public InvalidRatingException(String message) {
        super(message);
    }
}
