package com.primetech.primetech_store.common.application.exception;

import com.primetech.primetech_store.common.application.exception.basicException.NotFoundException;

public class ProductNotFoundException extends NotFoundException {
  public ProductNotFoundException(String message) {
    super(message);
  }
}
