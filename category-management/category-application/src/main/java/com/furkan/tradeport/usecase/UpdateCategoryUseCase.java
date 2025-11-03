package com.furkan.tradeport.usecase;

import com.furkan.tradeport.command.UpdateCategoryCommand;
import com.furkan.tradeport.model.Category;

public interface UpdateCategoryUseCase {
    Category update(String categoryId,UpdateCategoryCommand command);
}
