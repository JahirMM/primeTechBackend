package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.ConflictException;

public class MainProductImageAlreadyExistsException extends ConflictException {
    public MainProductImageAlreadyExistsException(String message) {
        super(message);
    }
}
