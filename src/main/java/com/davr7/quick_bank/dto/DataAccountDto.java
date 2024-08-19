package com.davr7.quick_bank.dto;

import com.davr7.quick_bank.domain.Account;

import java.util.UUID;

public record DataAccountDto(UUID id, String accNumber, DataCustomerDto owner) {
    public static DataAccountDto convert(Account account) {
        DataCustomerDto owner = new DataCustomerDto(account.getOwner().getId(), account.getOwner().getName());
        return new DataAccountDto(account.getId(), account.getAccNumber(), owner);
    }
}
