package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.InternalServerErrorException;

public class FileStorageException extends InternalServerErrorException {
    public FileStorageException(String message) {
        super(message);
    }
}
