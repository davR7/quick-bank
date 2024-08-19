package com.davr7.quick_bank.domain;

import com.davr7.quick_bank.common.EntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer extends EntityBase {
    @JoinColumn(nullable = false)
    private String name;

    @JoinColumn(nullable = false)
    private String cpf;

    @JoinColumn(nullable = false)
    private String email;

    @JoinColumn(nullable = false)
    private String phone;

    @JoinColumn(name = "date_birth" ,nullable = false)
    private LocalDate dateBirth;

    public Customer(UUID id, String name) {
        super(id);
        this.name = name;
    }

    public Customer(UUID id, String name, String cpf, String email, String phone, LocalDate dateBirth) {
        super(id);
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phone = phone;
        this.dateBirth = dateBirth;
    }
}
