package com.furkan.tradeport.adapter;

import com.furkan.tradeport.entity.JpaCustomerEntity;
import com.furkan.tradeport.model.Customer;
import com.furkan.tradeport.persistence.SpringDataCustomerRepository;
import com.furkan.tradeport.port.ReadCustomerPort;
import com.furkan.tradeport.valueobject.CustomerId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReadCustomerAdapter implements ReadCustomerPort {
    private final SpringDataCustomerRepository repository;

    public ReadCustomerAdapter(SpringDataCustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Customer> findByUserId(String userId) {
        return repository.findByUserId(userId).map(this::toDomain);
    }

    @Override
    public Optional<Customer> findById(String customerId) {
        return repository.findById(customerId).map(this::toDomain);
    }

    private Customer toDomain(JpaCustomerEntity entity) {
        return new Customer(CustomerId.of(entity.getCustomerId()));
    }
}
