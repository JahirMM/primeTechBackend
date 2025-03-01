package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.BadRequestException;

public class EmailChangeException extends BadRequestException {
    public EmailChangeException(String message) {
        super(message);
    }
}
