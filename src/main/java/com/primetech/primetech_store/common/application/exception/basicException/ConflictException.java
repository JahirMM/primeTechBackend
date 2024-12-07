package com.primetech.primetech_store.common.application.exception.basicException;

public class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
