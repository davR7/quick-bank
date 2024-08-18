package com.davr7.quick_bank.service;

import com.davr7.quick_bank.domain.Account;
import com.davr7.quick_bank.domain.Customer;
import com.davr7.quick_bank.dto.CreateAccountDto;
import com.davr7.quick_bank.dto.DataAccountDto;
import com.davr7.quick_bank.repository.AccountRepository;
import com.davr7.quick_bank.service.exceptions.AccountExistsException;
import com.davr7.quick_bank.service.exceptions.AccountNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record AccountService(AccountRepository accountRepo, CustomerService customerServ) {

    public List<DataAccountDto> findAllAccounts() {
        List<Account> customers = accountRepo.findAll();
        return customers.stream().map(DataAccountDto::convert).toList();
    }

    public DataAccountDto findByAccountNumber(String accNumber) {
        if (accNumber == null) {
            throw new IllegalArgumentException("accNumber argument is null");
        }
        Account account = accountRepo.findByAccNumber(accNumber).orElseThrow(AccountNotFoundException::new);
        return DataAccountDto.convert(account);
    }

    public DataAccountDto createAccount(String cpf, CreateAccountDto dto) {
        Customer savedOwner = customerServ.findCustomerByCpf(cpf);
        accountRepo.findByAccNumber(dto.accNumber()).ifPresent((accNumber) -> {
            throw new AccountExistsException();
        });
        Account account = CreateAccountDto.toAccount(dto, savedOwner);
        Account savedAccount = accountRepo.save(account);
        return DataAccountDto.convert(savedAccount);
    }
}
