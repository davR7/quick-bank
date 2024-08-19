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

    public DataCustomerDto findCustomerByCpf(String cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("cpf argument is null");
        }
        Customer savedCustomer = customerRepo.findByCpf(cpf).orElseThrow(CustomerNotFoundException::new);
        return DataCustomerDto.convert(savedCustomer);
    }

    public DataCustomerDto createCustomer(CreateCustomerDto dto) {
        customerRepo.findByCpf(dto.cpf()).ifPresent((cpf) -> {
            throw new CustomerExistsException();
        });
        Customer savedCustomer = customerRepo.save(CreateCustomerDto.toCostumer(dto));
        return DataCustomerDto.convert(savedCustomer);
    }
}
