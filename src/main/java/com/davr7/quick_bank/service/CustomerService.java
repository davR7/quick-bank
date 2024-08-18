package com.davr7.quick_bank.service;

import com.davr7.quick_bank.domain.Customer;
import com.davr7.quick_bank.dto.CreateCustomerDto;
import com.davr7.quick_bank.dto.DataCustomerDto;
import com.davr7.quick_bank.repository.CustomerRepository;
import com.davr7.quick_bank.service.exceptions.CustomerExistsException;
import com.davr7.quick_bank.service.exceptions.CustomerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record CustomerService(CustomerRepository customerRepo) {

    public List<DataCustomerDto> findAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
        return customers.stream().map(DataCustomerDto::convert).toList();
    }

    public Customer findCustomerByCpf(String cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("cpf argument is null");
        }
        return customerRepo.findByCpf(cpf).orElseThrow(CustomerNotFoundException::new);
    }

    public DataCustomerDto createCustomer(CreateCustomerDto dto) {
        customerRepo.findByCpf(dto.cpf()).ifPresent((cpf) -> {
            throw new CustomerExistsException();
        });
        Customer customer = CreateCustomerDto.toCostumer(dto);
        Customer savedCustomer = customerRepo.save(customer);
        return DataCustomerDto.convert(savedCustomer);
    }
}
