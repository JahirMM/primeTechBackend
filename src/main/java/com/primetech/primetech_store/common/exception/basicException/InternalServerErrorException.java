package com.primetech.primetech_store.common.exception.basicException;

public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}