package com.davr7.quick_bank.service.exceptions;

public class AccountExistsException extends RuntimeException {
    public AccountExistsException() {
        super("account exists");
    }
}
