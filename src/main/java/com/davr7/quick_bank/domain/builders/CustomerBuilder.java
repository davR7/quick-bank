package com.davr7.quick_bank.domain.builders;

import com.davr7.quick_bank.domain.Customer;
import com.davr7.quick_bank.dto.DataCustomerDto;

import java.time.LocalDate;
import java.util.UUID;

public class CustomerBuilder {
    private UUID id;
    private String name;
    private String cpf;
    private String email;
    private String phone;
    private LocalDate dateBirth;

    private static void createMockCustomer(CustomerBuilder customer) {
        customer.id = UUID.fromString("97d5a7c1-706a-4d51-a11f-ab0434f24f5b");
        customer.name = "Customer Mock";
        customer.cpf = "001012022032";
        customer.email = "customer.mock@test.com";
        customer.phone = "81985334455";
        customer.dateBirth = LocalDate.of(2000, 8, 18);
    }

    public static CustomerBuilder oneCustomer() {
        CustomerBuilder customer = new CustomerBuilder();
        CustomerBuilder.createMockCustomer(customer);
        return customer;
    }

    public CustomerBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder withCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public CustomerBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public CustomerBuilder withDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
        return this;
    }

    public Customer build() {
        return new Customer(id, name, cpf, email, phone, dateBirth);
    }
}
