package com.primetech.primetech_store.common.exception;

import com.primetech.primetech_store.common.exception.basicException.NotFoundException;

public class UserRoleAssignmentNotFoundException extends NotFoundException {
    public UserRoleAssignmentNotFoundException(String message) {
        super(message);
    }
}
