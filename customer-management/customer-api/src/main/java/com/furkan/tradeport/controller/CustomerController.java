package com.furkan.tradeport.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.furkan.tradeport.command.UpdateCustomerCommand;
import com.furkan.tradeport.dto.UpdateCustomerRequest;
import com.furkan.tradeport.dto.UpdateCustomerResponse;
import com.furkan.tradeport.model.AuthenticatedUser;
import com.furkan.tradeport.model.Customer;
import com.furkan.tradeport.usecase.ReadCustomerUseCase;
import com.furkan.tradeport.usecase.UpdateCustomerUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final ReadCustomerUseCase readCustomerUseCase;
    private final UpdateCustomerUseCase updateCustomerUseCase;

    public CustomerController(ReadCustomerUseCase readCustomerUseCase, UpdateCustomerUseCase updateCustomerUseCase) {
        this.readCustomerUseCase = readCustomerUseCase;
        this.updateCustomerUseCase = updateCustomerUseCase;
    }

    @GetMapping("/find/{userId}")
    public ResponseEntity<String>  readCustomer(@PathVariable("userId") String userId){
        Customer customer = readCustomerUseCase.findCustomerByUserId(userId);
        return ResponseEntity.ok(customer.getCustomerId().asString());
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateCustomerResponse> updateCustomer(@AuthenticationPrincipal AuthenticatedUser user,
                                                                 @RequestBody UpdateCustomerRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        UpdateCustomerCommand command = mapper.convertValue(request,UpdateCustomerCommand.class);
        Customer customer = updateCustomerUseCase.updateCustomer(user.customerId(), command);
        UpdateCustomerResponse result = new UpdateCustomerResponse(
                customer.getFullname().firstName(),
                customer.getFullname().lastName(),
                customer.getIdNumber().asString(),
                customer.getAddress().country(),
                customer.getAddress().city(),
                customer.getAddress().district(),
                customer.getAddress().street(),
                customer.getAddress().apartmentNumber(),
                customer.getAddress().doorNumber()
        );
        return ResponseEntity.ok(result);
    }
}

