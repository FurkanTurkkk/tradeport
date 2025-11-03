package com.furkan.tradeport.service;

import com.furkan.tradeport.port.CategoryRepositoryPort;
import com.furkan.tradeport.usecase.DeleteCategoryUseCase;
import com.furkan.tradeport.valueobject.CategoryId;

public class DeleteCategoryService implements DeleteCategoryUseCase {

    private final CategoryRepositoryPort categoryRepositoryPort;

    public DeleteCategoryService(CategoryRepositoryPort categoryRepositoryPort) {
        this.categoryRepositoryPort = categoryRepositoryPort;
    }

    @Override
    public void deleteCategory(CategoryId categoryId) {
        categoryRepositoryPort.deleteById(categoryId);
    }
}
