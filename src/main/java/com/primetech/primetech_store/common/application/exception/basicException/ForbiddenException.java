package com.primetech.primetech_store.common.application.exception.basicException;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}
