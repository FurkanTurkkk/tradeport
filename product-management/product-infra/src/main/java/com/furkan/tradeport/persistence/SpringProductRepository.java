package com.furkan.tradeport.persistence;

import com.furkan.tradeport.entity.JpaProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringProductRepository extends JpaRepository<JpaProductEntity,String> {
        Optional<JpaProductEntity> findByProductCode(String productCode);
        List<JpaProductEntity> findByCategoryId(String categoryId);
}
