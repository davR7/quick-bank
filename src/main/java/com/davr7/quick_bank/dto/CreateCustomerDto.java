package com.davr7.quick_bank.dto;

import com.davr7.quick_bank.domain.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record CreateCustomerDto(
        @NotBlank(message = "name is required")
        String name,
        @NotBlank(message = "cpf is required")
        @Length(min = 11, max = 11, message = "cpf must have a maximum of {max} characters")
        String cpf,
        @NotBlank(message = "email is required")
        @Email(message = "email is invalid")
        String email,
        @NotBlank(message = "phone is required")
        @Length(min = 11, max = 11, message = "phone must have a maximum of {max} characters")
        String phone,
        @NotBlank(message = "dateBirth is required")
        String dateBirth
        ) {
    public static Customer toCostumer(CreateCustomerDto dto){
        return new Customer(dto.name(), dto.cpf(), dto.email(), dto.phone(), LocalDate.parse(dto.dateBirth()));
    }
}
