package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.BadRequestException;

public class InvalidFieldFormatException extends BadRequestException {
    public InvalidFieldFormatException(String message) {
        super(message);
    }
}
