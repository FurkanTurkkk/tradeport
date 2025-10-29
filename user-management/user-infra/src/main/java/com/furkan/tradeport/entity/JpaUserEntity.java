package com.furkan.tradeport.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "USERS")
public class JpaUserEntity {
    @Id
    @Column(name = "ID" , length = 36)
    private String id;

    @Column(name = "EMAIL" , unique = true , nullable = false)
    private String email;

    @Column(name = "PASSWORD" , nullable = false)
    private String passwordHash;

    @Column(name = "ROLES" , nullable = false)
    private String roles;

    @Column(name = "CREATED_AT" , nullable = false)
    private Instant createdAt;

    protected JpaUserEntity() {}

    public JpaUserEntity(String id, String email, String passwordHash, String roles, Instant createdAt) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.roles = roles;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getRoles() {
        return roles;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
