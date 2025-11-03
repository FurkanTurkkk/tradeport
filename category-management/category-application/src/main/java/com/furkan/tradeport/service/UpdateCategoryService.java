package com.furkan.tradeport.service;

import com.furkan.tradeport.command.UpdateCategoryCommand;
import com.furkan.tradeport.model.Category;
import com.furkan.tradeport.port.CategoryRepositoryPort;
import com.furkan.tradeport.usecase.UpdateCategoryUseCase;
import com.furkan.tradeport.valueobject.CategoryId;
import com.furkan.tradeport.valueobject.CategoryName;

public class UpdateCategoryService implements UpdateCategoryUseCase {
    private final CategoryRepositoryPort  categoryRepositoryPort;

    public UpdateCategoryService(CategoryRepositoryPort categoryRepositoryPort) {
        this.categoryRepositoryPort = categoryRepositoryPort;
    }

    @Override
    public Category update(String categoryId, UpdateCategoryCommand command) {
        Category category =
                new Category(CategoryId.of(categoryId), CategoryName.of(command.getCategoryName()),CategoryId.of(command.getParentId()));
        return categoryRepositoryPort.update(category);
    }
}
