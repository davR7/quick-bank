package com.davr7.quick_bank.domain.builders;

import com.davr7.quick_bank.domain.Account;
import com.davr7.quick_bank.domain.Customer;
import com.davr7.quick_bank.domain.enums.AccountType;
import com.davr7.quick_bank.dto.DataAccountDto;
import com.davr7.quick_bank.dto.DataCustomerDto;

import java.util.UUID;

public class AccountBuilder {
    private UUID id;
    private String accNumber;
    private AccountType accType;
    private Double balance;
    private Customer owner;

    private static void createMockAccount(AccountBuilder account) {
        account.id = UUID.fromString("2652527c-f8b8-4101-b1fa-263051b79612");
        account.accNumber = "4111211131115111";
        account.accType = AccountType.SAVINGS;
        account.balance = 220.0;
        account.owner = CustomerBuilder.oneCustomer().build();
    }

    public static AccountBuilder oneAccount() {
        AccountBuilder account = new AccountBuilder();
        AccountBuilder.createMockAccount(account);
        return account;
    }

    public AccountBuilder withId(UUID id) {
        this.id = id;
        return this;
    }
    public AccountBuilder withAccNumber(String accNumber) {
        this.accNumber = accNumber;
        return this;
    }

    public AccountBuilder withAccType(AccountType accType) {
        this.accType = accType;
        return this;
    }

    public AccountBuilder withBalance(Double balance) {
        this.balance = balance;
        return this;
    }

    public Account build() {
        return new Account(id, accNumber, accType, balance, owner);
    }
}
