package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.BadRequestException;

public class InvalidFileFormatException extends BadRequestException {
    public InvalidFileFormatException(String message) {
        super(message);
    }
}
