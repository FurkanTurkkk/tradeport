package com.furkan.tradeport.service;

import com.furkan.tradeport.command.UpdateCustomerCommand;
import com.furkan.tradeport.model.Customer;
import com.furkan.tradeport.port.ReadCustomerPort;
import com.furkan.tradeport.port.UpdateCustomerPort;
import com.furkan.tradeport.usecase.UpdateCustomerUseCase;
import com.furkan.tradeport.valueobject.Address;
import com.furkan.tradeport.valueobject.FullName;
import com.furkan.tradeport.valueobject.IdNumber;

import java.util.Optional;

public class UpdateCustomerService implements UpdateCustomerUseCase {
    private final ReadCustomerPort readCustomerPort;
    private final UpdateCustomerPort updateCustomerPort;

    public UpdateCustomerService(ReadCustomerPort readCustomerPort, UpdateCustomerPort updateCustomerPort) {
        this.readCustomerPort = readCustomerPort;
        this.updateCustomerPort = updateCustomerPort;
    }

    @Override
    public Customer updateCustomer(String customerId,UpdateCustomerCommand command) {
        Optional<Customer> customer = readCustomerPort.findById(customerId);
        if(customer.isEmpty()) {
            throw new RuntimeException("Customer can not found");
        }
        changeFullname(customer.get(),command);
        changeIdNumber(customer.get(),command);
        changeAddress(customer.get(),command);
        return updateCustomerPort.updateCustomer(customer.get());
    }

    private void changeFullname(Customer customer, UpdateCustomerCommand command) {
        if(command.firstname() != null && command.lastname() != null) {
            customer.changeFullName(new FullName(command.firstname(),command.lastname()));
        }
    }

    private void changeIdNumber(Customer customer, UpdateCustomerCommand command) {
        if(command.idNumber() != null) {
            customer.changeIdNumber(new IdNumber(command.idNumber()));
        }
    }

    private void changeAddress(Customer customer, UpdateCustomerCommand command) {
        if (!checkCommandForChange(command)) return;

        Address currentAddress = customer.getAddress();

        if (currentAddress == null) {
            Address newAddress = createAddress(command);
            customer.changeAddress(newAddress);
            return;
        }

        Address updatedAddress = updateAddress(command,currentAddress);
        customer.changeAddress(updatedAddress);
    }

    private boolean checkCommandForChange(UpdateCustomerCommand command) {
        return  command.country() != null ||
                command.city() != null ||
                command.district() != null ||
                command.street() != null ||
                command.apartmentNumber() != null ||
                command.doorNumber() != null;
    }

    private Address updateAddress(UpdateCustomerCommand command, Address currentAddress) {
        return new Address(
                command.country() != null ? command.country() : currentAddress.country(),
                command.city() != null ? command.city() : currentAddress.city(),
                command.district() != null ? command.district() : currentAddress.district(),
                command.street() != null ? command.street() : currentAddress.street(),
                command.apartmentNumber() != null ? command.apartmentNumber() : currentAddress.apartmentNumber(),
                command.doorNumber() != null ? command.doorNumber() : currentAddress.doorNumber()
        );
    }

    private Address createAddress(UpdateCustomerCommand command) {
        return new Address(
                command.country(),
                command.city(),
                command.district(),
                command.street(),
                command.apartmentNumber(),
                command.doorNumber()
        );
    }


}
