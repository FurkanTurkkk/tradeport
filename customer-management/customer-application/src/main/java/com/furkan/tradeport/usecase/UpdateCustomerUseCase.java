package com.furkan.tradeport.usecase;

import com.furkan.tradeport.command.UpdateCustomerCommand;
import com.furkan.tradeport.model.Customer;

public interface UpdateCustomerUseCase {
    Customer updateCustomer(String customerId,UpdateCustomerCommand command);
}
