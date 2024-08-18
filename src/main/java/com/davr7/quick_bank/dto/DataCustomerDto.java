package com.davr7.quick_bank.dto;

import com.davr7.quick_bank.domain.Customer;

import java.time.LocalDate;
import java.util.UUID;

public record DataCustomerDto(UUID id, String name, String email, String phone) {
    public static DataCustomerDto convert(Customer customer) {
        return new DataCustomerDto(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone());
    }
}
