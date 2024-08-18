package com.davr7.quick_bank.dto;

import com.davr7.quick_bank.domain.Account;
import com.davr7.quick_bank.domain.Customer;
import com.davr7.quick_bank.domain.enums.AccountType;

import java.util.UUID;

public record DataAccountDto(UUID id, String accNumber, AccountType accType, Double balance, DataCustomerDto owner) {
    public static DataAccountDto convert(Account account) {
        DataCustomerDto owner = new DataCustomerDto(account.getOwner().getId(), account.getOwner().getName(), account.getOwner().getEmail(), account.getOwner().getPhone());
        return new DataAccountDto(account.getId(), account.getAccNumber(), account.getAccType(), account.getBalance(), owner);
    }
}
