package com.primetech.primetech_store.common.application.exception.basicException;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
