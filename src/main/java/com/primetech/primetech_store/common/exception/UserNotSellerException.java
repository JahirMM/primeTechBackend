package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.ForbiddenException;

public class UserNotSellerException extends ForbiddenException {
    public UserNotSellerException(String message) {
        super(message);
    }
}