package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.NotFoundException;

public class UserImageNotFoundException extends NotFoundException {
    public UserImageNotFoundException(String message) {
        super(message);
    }
}
