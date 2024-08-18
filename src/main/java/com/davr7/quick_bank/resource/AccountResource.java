package com.davr7.quick_bank.resource;

import com.davr7.quick_bank.dto.DataAccountDto;
import com.davr7.quick_bank.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "accounts")
public record AccountResource(AccountService accountServ) {
    @GetMapping
    public ResponseEntity<List<DataAccountDto>> handleFindAllCustomers() {
        List<DataAccountDto> data = accountServ.findAllAccounts();
        return ResponseEntity.ok().body(data);
    }

    @GetMapping(value = "/{accNumber}")
    public ResponseEntity<DataAccountDto> handleFindByAccountNumber(@PathVariable String accNumber) {
        DataAccountDto data = accountServ.findByAccountNumber(accNumber);
        return ResponseEntity.ok().body(data);
    }
}
