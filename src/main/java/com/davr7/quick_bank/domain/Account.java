package com.davr7.quick_bank.domain;

import com.davr7.quick_bank.common.EntityBase;
import com.davr7.quick_bank.domain.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account extends EntityBase {
    @JoinColumn(name = "acc_number", nullable = false)
    private String accNumber;

    @JoinColumn(name = "acc_type", nullable = false)
    private AccountType accType;

    @JoinColumn(nullable = false)
    private Double balance;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer owner;

    public Account(String accNumber, AccountType accType, Customer owner) {
        initialBalance();
        this.accNumber = accNumber;
        this.accType = accType;
        this.owner = owner;
    }

    public void initialBalance() {
        balance = 0.0;
    }
}
