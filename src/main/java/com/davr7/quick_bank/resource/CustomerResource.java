package com.davr7.quick_bank.resource;

import com.davr7.quick_bank.domain.Customer;
import com.davr7.quick_bank.dto.CreateCustomerDto;
import com.davr7.quick_bank.dto.DataCustomerDto;
import com.davr7.quick_bank.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "customers")
public record CustomerResource(CustomerService customerServ) {

    @PostMapping
    public ResponseEntity<DataCustomerDto> handleCreateCustomer(@RequestBody @Valid CreateCustomerDto dto) {
        DataCustomerDto data = customerServ.createCustomer(dto);
        return ResponseEntity.status(201).body(data);
    }

    @GetMapping
    public ResponseEntity<List<DataCustomerDto>> handleFindAllCustomers() {
        List<DataCustomerDto> data = customerServ.findAllCustomers();
        return ResponseEntity.ok().body(data);
    }

    @GetMapping(value = "/{cpf}")
    public ResponseEntity<Customer> handleFindCustomerByCpf(@PathVariable String cpf) {
        Customer data = customerServ.findCustomerByCpf(cpf);
        return ResponseEntity.ok().body(data);
    }
}