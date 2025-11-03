package com.furkan.tradeport.port;

import com.furkan.tradeport.model.Category;
import com.furkan.tradeport.valueobject.CategoryId;
import com.furkan.tradeport.valueobject.CategoryName;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryPort {
    Optional<Category> findById(CategoryId id);
    Optional<Category> findByName(CategoryName name);
    List<Category> findAll();
    void save(Category category);
    void deleteById(CategoryId id);
    Category update(Category category);
}
