package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.ForbiddenException;

public class UserNotSellerException extends ForbiddenException {
    public UserNotSellerException(String message) {
        super(message);
    }
}
