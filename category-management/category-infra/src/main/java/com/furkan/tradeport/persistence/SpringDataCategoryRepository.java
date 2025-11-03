package com.furkan.tradeport.persistence;

import com.furkan.tradeport.entity.JpaCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataCategoryRepository extends JpaRepository<JpaCategoryEntity,String> {
    Optional<JpaCategoryEntity> findByName(String name);
    Optional<JpaCategoryEntity> findByParentId(String parentId);
}
