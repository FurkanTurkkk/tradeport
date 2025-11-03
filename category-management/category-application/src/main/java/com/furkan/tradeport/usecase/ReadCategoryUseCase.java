package com.furkan.tradeport.usecase;

import com.furkan.tradeport.model.Category;
import com.furkan.tradeport.valueobject.CategoryId;

import java.util.List;

public interface ReadCategoryUseCase {
    Category findCategoryById(CategoryId categoryId);
    List<Category> findAllCategories();
}
