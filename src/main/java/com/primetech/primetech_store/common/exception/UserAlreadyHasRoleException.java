package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.ConflictException;

public class UserAlreadyHasRoleException extends ConflictException {
    public UserAlreadyHasRoleException(String message) {
        super(message);
    }
}
