package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.ConflictException;

public class ScreenAlreadyExistsException extends ConflictException {
    public ScreenAlreadyExistsException(String message) {
        super(message);
    }
}
