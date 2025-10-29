package com.furkan.tradeport.usecase;

import com.furkan.tradeport.model.Customer;

public interface ReadCustomerUseCase {
    Customer findCustomerByCustomerId(String customerId);
    Customer findCustomerByUserId(String userId);
}
