package com.furkan.tradeport.usecase;

import com.furkan.tradeport.command.CreateCustomerCommand;
import com.furkan.tradeport.valueobject.CustomerId;

public interface CreateCustomerUseCase {
    CustomerId createCustomer(CreateCustomerCommand createCustomerCommand);
}
