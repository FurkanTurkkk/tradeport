package com.furkan.tradeport.port;

import com.furkan.tradeport.model.Customer;

public interface CreateCustomerPort {
    Customer createCustomer(String userId);
}
