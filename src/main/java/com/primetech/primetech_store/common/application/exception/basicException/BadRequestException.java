package com.primetech.primetech_store.common.application.exception.basicException;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
