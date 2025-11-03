package com.furkan.tradeport.adapter;

import com.furkan.tradeport.entity.JpaCustomerEntity;
import com.furkan.tradeport.model.Customer;
import com.furkan.tradeport.persistence.SpringDataCustomerRepository;
import com.furkan.tradeport.port.CreateCustomerPort;
import com.furkan.tradeport.valueobject.CustomerId;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerAdapter implements CreateCustomerPort {
    private final SpringDataCustomerRepository repository;

    public CreateCustomerAdapter(SpringDataCustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer createCustomer(String userId) {
        JpaCustomerEntity customerEntity = repository.save(new JpaCustomerEntity(CustomerId.random().asString(), userId));
        return toDomain(customerEntity);
    }

    private Customer toDomain(JpaCustomerEntity customerEntity) {
        CustomerId customerId = CustomerId.of(customerEntity.getCustomerId());
        return new Customer(customerId);
    }
}
