package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.NotFoundException;

public class ShoppingCartNotFoundException extends NotFoundException {
    public ShoppingCartNotFoundException(String message) {
        super(message);
    }
}
