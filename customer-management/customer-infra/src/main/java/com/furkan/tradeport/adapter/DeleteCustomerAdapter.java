package com.furkan.tradeport.adapter;

import com.furkan.tradeport.entity.JpaCustomerEntity;
import com.furkan.tradeport.persistence.SpringDataCustomerRepository;
import com.furkan.tradeport.port.DeleteCustomerPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeleteCustomerAdapter implements DeleteCustomerPort {
    private final SpringDataCustomerRepository repository;

    public DeleteCustomerAdapter(SpringDataCustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deleteCustomerByUserId(String userId) {
        Optional<JpaCustomerEntity> customer = repository.findByUserId(userId);
        customer.ifPresent(repository::delete);
    }
}
