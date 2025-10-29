package com.furkan.tradeport.persistence;

import com.furkan.tradeport.entity.JpaUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<JpaUserEntity,String> {
    Optional<JpaUserEntity> findByEmail(String email);
}
