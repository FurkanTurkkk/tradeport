package com.furkan.tradeport.service;

import com.furkan.tradeport.exception.CategoryNotFoundException;
import com.furkan.tradeport.model.Category;
import com.furkan.tradeport.port.CategoryRepositoryPort;
import com.furkan.tradeport.usecase.ReadCategoryUseCase;
import com.furkan.tradeport.valueobject.CategoryId;

import java.util.List;
import java.util.Optional;

public class ReadCategoryService implements ReadCategoryUseCase {

    private final CategoryRepositoryPort categoryRepositoryPort;

    public ReadCategoryService(CategoryRepositoryPort categoryRepositoryPort) {
        this.categoryRepositoryPort = categoryRepositoryPort;
    }

    @Override
    public Category findCategoryById(CategoryId id) {
        Optional<Category> category = categoryRepositoryPort.findById(id);
        if(category.isPresent()) {
            return category.get();
        }
        throw new CategoryNotFoundException("Category could not found by id : " +id.getValue());
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepositoryPort.findAll();
    }
}
