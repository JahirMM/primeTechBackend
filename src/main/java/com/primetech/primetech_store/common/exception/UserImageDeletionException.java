package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.InternalServerErrorException;

public class UserImageDeletionException extends InternalServerErrorException {
    public UserImageDeletionException(String message) {
        super(message);
    }
}
