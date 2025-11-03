package com.furkan.tradeport.usecase;

import com.furkan.tradeport.valueobject.CategoryId;

public interface DeleteCategoryUseCase {
    void deleteCategory(CategoryId categoryId);
}
