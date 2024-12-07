package com.primetech.primetech_store.common.application.exception.basicException;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
