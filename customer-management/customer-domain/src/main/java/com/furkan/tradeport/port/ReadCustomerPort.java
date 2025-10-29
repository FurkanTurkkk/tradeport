package com.furkan.tradeport.port;

import com.furkan.tradeport.model.Customer;

import java.util.Optional;

public interface ReadCustomerPort {
    Optional<Customer> findByUserId(String userId);
    Optional<Customer> findById(String customerId);
}
