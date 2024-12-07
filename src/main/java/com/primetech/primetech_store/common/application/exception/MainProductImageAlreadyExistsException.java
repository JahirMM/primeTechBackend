package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.ConflictException;

public class MainProductImageAlreadyExistsException extends ConflictException {
    public MainProductImageAlreadyExistsException(String message) {
        super(message);
    }
}
