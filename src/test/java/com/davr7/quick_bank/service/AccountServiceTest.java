package com.davr7.quick_bank.service;

import com.davr7.quick_bank.domain.Account;
import com.davr7.quick_bank.domain.Customer;
import com.davr7.quick_bank.domain.builders.AccountBuilder;
import com.davr7.quick_bank.dto.CreateAccountDto;
import com.davr7.quick_bank.dto.DataAccountDto;
import com.davr7.quick_bank.dto.DataCustomerDto;
import com.davr7.quick_bank.repository.AccountRepository;
import com.davr7.quick_bank.service.exceptions.AccountNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    AccountRepository accountRepo;

    @Mock
    CustomerService customerService;

    @InjectMocks
    AccountService accountServ;

    Account account;

    Customer customer;

    CreateAccountDto accountBody;

    DataAccountDto accountData;

    @BeforeEach
    public void setUp() {
        account = AccountBuilder.oneAccount().build();
        customer = account.getOwner();
        accountBody = new CreateAccountDto(account.getAccNumber(), account.getAccType(), account.getBalance());
        accountData = DataAccountDto.convert(account);
    }

    @Test
    @DisplayName("Should trigger exception if accNumber is null")
    void shouldTriggerExceptionIfCpfIsNull() {
        String error = assertThrows(IllegalArgumentException.class, () -> accountServ.findByAccountNumber(null)).getMessage();
        assertEquals("accNumber argument is null", error);
    }

    @Test
    @DisplayName("Should trigger exception if not found account")
    public void shouldTriggerExceptionIfNotFoundAccount() {
        String error = assertThrows(AccountNotFoundException.class,
                () -> accountServ.findByAccountNumber("4111 1111 1111 1111")).getMessage();
        assertEquals("account not found", error);
    }

    @Test
    @DisplayName("Should find account by accNumber with successfully")
    public void shouldFindAccountByAccNumberWithSuccessfully() {
        when(accountRepo.findByAccNumber("4111211131115111")).thenReturn(Optional.of(account));
        Account result = accountServ.findByAccountNumber("4111211131115111");
        assertEquals("4111211131115111", result.getAccNumber());
    }

    @Test
    @DisplayName("Should create account savings with successfully")
    public void shouldCreateAccountSavingsWithSuccessfully() {
        DataCustomerDto customerData = new DataCustomerDto(customer.getId(), customer.getName());
        when(customerService.findCustomerByCpf("001012022032")).thenReturn(customerData);
        when(accountRepo.save(any(Account.class))).thenReturn(account);
        DataAccountDto result = accountServ.createAccount("001012022032", accountBody);
        verify(accountRepo, atMostOnce()).findByAccNumber(account.getAccNumber());
        assertNotNull(result.accNumber());
    }

    @Test
    @DisplayName("Should find accounts with successfully")
    public void shouldFindAccountsWithSuccessfully() {
        List<Account> accounts = Collections.singletonList(account);
        List<DataAccountDto> accountsData = Collections.singletonList(accountData);
        when(accountRepo.findAll()).thenReturn(accounts);
        List<DataAccountDto> result = accountServ.findAllAccounts();
        assertEquals(accountsData, result);
    }
}