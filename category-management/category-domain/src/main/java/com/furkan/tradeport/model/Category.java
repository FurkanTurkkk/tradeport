package com.furkan.tradeport.model;

import com.furkan.tradeport.valueobject.CategoryId;
import com.furkan.tradeport.valueobject.CategoryName;

import java.time.Instant;

public class Category {

    private final CategoryId id;
    private CategoryName name;
    private CategoryId parentId;
    private Instant createdAt;
    private Instant updatedAt;

    public Category(CategoryId id, CategoryName name, CategoryId parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public CategoryId getId() {
        return id;
    }

    public CategoryName getName() {
        return name;
    }

    public CategoryId getParentId() {
        return parentId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void markUpdated() {
        this.updatedAt = Instant.now();
    }
}
