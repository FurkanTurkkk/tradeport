package com.furkan.tradeport.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "category")
public class JpaCategoryEntity {

    @Id
    private String id;

    private String name;
    private String parentId;
    private Instant createdAt;
    private Instant updatedAt;

    public JpaCategoryEntity(String id, String name, String parentId, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public JpaCategoryEntity() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParentId() {
        return parentId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
