package com.furkan.tradeport.service;

import com.furkan.tradeport.command.CreateCustomerCommand;
import com.furkan.tradeport.model.Customer;
import com.furkan.tradeport.port.CreateCustomerPort;
import com.furkan.tradeport.usecase.CreateCustomerUseCase;
import com.furkan.tradeport.valueobject.CustomerId;

public class CreateCustomerService implements CreateCustomerUseCase {
    private final CreateCustomerPort createCustomerPort;

    public CreateCustomerService(CreateCustomerPort createCustomerPort) {
        this.createCustomerPort = createCustomerPort;
    }

    @Override
    public CustomerId createCustomer(CreateCustomerCommand createCustomerCommand) {
        Customer customer = createCustomerPort.createCustomer(createCustomerCommand.userId());
        return customer.getCustomerId();
    }
}
