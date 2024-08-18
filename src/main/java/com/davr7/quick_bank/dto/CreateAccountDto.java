package com.davr7.quick_bank.dto;

import com.davr7.quick_bank.domain.Account;
import com.davr7.quick_bank.domain.Customer;
import com.davr7.quick_bank.domain.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CreateAccountDto(
        @NotBlank(message = "accNumber is required")
        @Length(min = 16, max = 16, message = "accNumber must have a maximum of {max} characters")
        String accNumber,
        @NotNull(message = "accType is required")
        AccountType accType,
        @NotNull(message = "balance is required")
        Double balance
    ) {
    public static Account toAccount(CreateAccountDto dto, Customer owner) {
        return new Account(dto.accNumber(), dto.accType, dto.balance, owner);
    }
}
