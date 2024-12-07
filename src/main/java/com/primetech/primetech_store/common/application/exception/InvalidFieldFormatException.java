package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.BadRequestException;

public class InvalidFieldFormatException extends BadRequestException {
    public InvalidFieldFormatException(String message) {
        super(message);
    }
}
