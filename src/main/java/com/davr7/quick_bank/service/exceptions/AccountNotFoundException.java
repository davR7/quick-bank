package com.davr7.quick_bank.service.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException() {
        super("account not found");
    }
}
