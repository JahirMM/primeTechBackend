package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.ConflictException;

public class UserAlreadyHasRoleException extends ConflictException {
    public UserAlreadyHasRoleException(String message) {
        super(message);
    }
}
