package com.davr7.quick_bank.service;

import com.davr7.quick_bank.domain.Customer;
import com.davr7.quick_bank.domain.builders.CustomerBuilder;
import com.davr7.quick_bank.dto.CreateCustomerDto;
import com.davr7.quick_bank.dto.DataCustomerDto;
import com.davr7.quick_bank.repository.CustomerRepository;
import com.davr7.quick_bank.service.exceptions.CustomerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    CustomerRepository customerRepo;

    @InjectMocks
    CustomerService customerServ;

    Customer customer;

    CreateCustomerDto customerBody;

    DataCustomerDto customerData;

    @BeforeEach
    public void setUp() {
        customer = CustomerBuilder.oneCustomer().build();
        customerBody = new CreateCustomerDto(customer.getName(), customer.getCpf(), customer.getCpf(), customer.getPhone(), "2000-08-18");
        customerData = DataCustomerDto.convert(customer);
    }

    @Test
    @DisplayName("Should trigger exception if cpf is null")
    void shouldTriggerExceptionIfCpfIsNull() {
        String error = assertThrows(IllegalArgumentException.class, () -> customerServ.findCustomerByCpf(null)).getMessage();
        assertEquals("cpf argument is null", error);
    }

    @Test
    @DisplayName("Should trigger exception if not found customer by cpf")
    void shouldTriggerExceptionIfNotFoundCustomerByCpf() {
        String error = assertThrows(CustomerNotFoundException.class, () -> customerServ.findCustomerByCpf("001012022032"))
                .getMessage();
        assertEquals("customer not found", error);
    }

    @Test
    @DisplayName("Should find customer by cpf with successfully")
    public void shouldFindCustomerByCpfWithSuccessfully() {
        when(customerRepo.findByCpf("001012022032")).thenReturn(Optional.of(customer));
        DataCustomerDto result = customerServ.findCustomerByCpf("001012022032");
        assertEquals(customerData.id(), result.id());
    }

    @Test
    @DisplayName("Should create customer with successfully")
    public void shouldCreateCustomerWithSuccessfully() {
        when(customerRepo.save(Mockito.any(Customer.class))).thenReturn(customer);
        DataCustomerDto result = customerServ.createCustomer(customerBody);
        Mockito.verify(customerRepo, atMostOnce()).findByCpf("001012022032");
        assertNotNull(result.id());
    }

    @Test
    @DisplayName("Should find customers with successfully")
    public void shouldFindCustomersWithSuccessfully() {
        List<Customer> customers = Collections.singletonList(customer);
        List<DataCustomerDto> customersData = Collections.singletonList(customerData);
        when(customerRepo.findAll()).thenReturn(customers);
        List<DataCustomerDto> result = customerServ.findAllCustomers();
        assertEquals(customersData, result);
    }
}