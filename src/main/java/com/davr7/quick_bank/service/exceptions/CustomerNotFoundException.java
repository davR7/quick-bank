package com.davr7.quick_bank.service.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException() {
        super("customer not found");
    }
}
