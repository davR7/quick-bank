package com.davr7.quick_bank.repository;

import com.davr7.quick_bank.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByAccNumber(String accNumber);
}
