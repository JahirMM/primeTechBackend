package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.InternalServerErrorException;

public class UserImageDeletionException extends InternalServerErrorException {
    public UserImageDeletionException(String message) {
        super(message);
    }
}
