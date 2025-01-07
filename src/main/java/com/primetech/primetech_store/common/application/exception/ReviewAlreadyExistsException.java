package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.ConflictException;

public class ReviewAlreadyExistsException extends ConflictException {
    public ReviewAlreadyExistsException(String message) {
        super(message);
    }
}
