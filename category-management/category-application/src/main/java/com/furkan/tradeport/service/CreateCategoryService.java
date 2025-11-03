package com.furkan.tradeport.service;

import com.furkan.tradeport.command.CreateCategoryCommand;
import com.furkan.tradeport.exception.CategoryAlreadyExistsException;
import com.furkan.tradeport.model.Category;
import com.furkan.tradeport.port.CategoryRepositoryPort;
import com.furkan.tradeport.usecase.CreateCategoryUseCase;
import com.furkan.tradeport.valueobject.CategoryId;
import com.furkan.tradeport.valueobject.CategoryName;

import java.util.Optional;

public class CreateCategoryService implements CreateCategoryUseCase {

    private final CategoryRepositoryPort categoryRepositoryPort;

    public CreateCategoryService(CategoryRepositoryPort categoryRepositoryPort) {
        this.categoryRepositoryPort = categoryRepositoryPort;
    }

    @Override
    public Category create(CreateCategoryCommand command) {
        CategoryName categoryName = CategoryName.of(command.getName());
        categoryRepositoryPort.findByName(categoryName).ifPresent(existing -> {
            throw new CategoryAlreadyExistsException("Category already exist : " + categoryName);
        });
        Category category = new Category(
                CategoryId.random(),
                CategoryName.of(command.getName()),
                CategoryId.of(command.getParentId()));
        categoryRepositoryPort.save(category);
        return category;
    }
}
