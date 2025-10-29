package com.furkan.tradeport.controller;

import com.furkan.tradeport.model.Customer;
import com.furkan.tradeport.usecase.ReadCustomerUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final ReadCustomerUseCase readCustomerUseCase;

    public CustomerController(ReadCustomerUseCase readCustomerUseCase) {
        this.readCustomerUseCase = readCustomerUseCase;
    }

    @GetMapping("/find/{userId}")
    public ResponseEntity<String>  readCustomer(@PathVariable("userId") String userId){
        Customer customer = readCustomerUseCase.findCustomerByUserId(userId);
        return ResponseEntity.ok(customer.getCustomerId().asString());
    }
}

