package com.davr7.quick_bank.service.exceptions;

public class CustomerExistsException extends RuntimeException {
    public CustomerExistsException() {
        super("customer exists");
    }
}
