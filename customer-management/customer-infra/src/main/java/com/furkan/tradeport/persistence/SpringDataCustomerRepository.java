package com.furkan.tradeport.persistence;

import com.furkan.tradeport.entity.JpaCustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataCustomerRepository extends JpaRepository<JpaCustomerEntity, String> {
    Optional<JpaCustomerEntity> findByUserId(String userId);
    Optional<JpaCustomerEntity> findByCustomerId(String customerId);
    Optional<JpaCustomerEntity> findByIdNumber(String idNumber);
}
