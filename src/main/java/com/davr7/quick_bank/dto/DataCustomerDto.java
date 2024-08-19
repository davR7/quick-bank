package com.davr7.quick_bank.dto;

import com.davr7.quick_bank.domain.Customer;

import java.util.UUID;

public record DataCustomerDto(UUID id, String name) {
    public static DataCustomerDto convert(Customer customer) {
        return new DataCustomerDto(customer.getId(), customer.getName());
    }
}
