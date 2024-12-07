package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.InternalServerErrorException;

public class FileStorageException extends InternalServerErrorException {
    public FileStorageException(String message) {
        super(message);
    }
}
