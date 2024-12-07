package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.BadRequestException;

public class InvalidFileFormatException extends BadRequestException {
    public InvalidFileFormatException(String message) {
        super(message);
    }
}
