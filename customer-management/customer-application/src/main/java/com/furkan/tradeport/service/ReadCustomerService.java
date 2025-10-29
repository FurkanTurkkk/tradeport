package com.furkan.tradeport.service;

import com.furkan.tradeport.model.Customer;
import com.furkan.tradeport.port.ReadCustomerPort;
import com.furkan.tradeport.usecase.ReadCustomerUseCase;

import java.util.Optional;

public class ReadCustomerService implements ReadCustomerUseCase {

    private final ReadCustomerPort  readCustomerPort;

    public ReadCustomerService(ReadCustomerPort readCustomerPort) {
        this.readCustomerPort = readCustomerPort;
    }

    @Override
    public Customer findCustomerByCustomerId(String customerId) {
        Optional<Customer> customer = readCustomerPort.findById(customerId);
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new RuntimeException("Customer not found");
    }

    @Override
    public Customer findCustomerByUserId(String userId) {
        Optional<Customer> customer = readCustomerPort.findByUserId(userId);
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new RuntimeException("Customer not found");
    }
}
