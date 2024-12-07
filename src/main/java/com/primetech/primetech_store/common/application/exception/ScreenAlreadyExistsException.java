package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.ConflictException;

public class ScreenAlreadyExistsException extends ConflictException {
    public ScreenAlreadyExistsException(String message) {
        super(message);
    }
}
