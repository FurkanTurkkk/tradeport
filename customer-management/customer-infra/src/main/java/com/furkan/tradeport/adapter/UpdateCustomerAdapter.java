package com.furkan.tradeport.adapter;

import com.furkan.tradeport.entity.JpaCustomerEntity;
import com.furkan.tradeport.model.Customer;
import com.furkan.tradeport.persistence.SpringDataCustomerRepository;
import com.furkan.tradeport.port.UpdateCustomerPort;

import java.util.Optional;

public class UpdateCustomerAdapter implements UpdateCustomerPort {
    private final SpringDataCustomerRepository repository;

    public UpdateCustomerAdapter(SpringDataCustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        String customerId = customer.getCustomerId().asString();
        Optional<JpaCustomerEntity> customerEntity = repository.findByCustomerId(customerId);
        if(customerEntity.isPresent()){
            JpaCustomerEntity updatedCustomer = updateCustomerEntity(customerEntity.get());
        }
        return null;
    }

    private JpaCustomerEntity updateCustomerEntity(JpaCustomerEntity existingCustomerEntity) {

    }
}
