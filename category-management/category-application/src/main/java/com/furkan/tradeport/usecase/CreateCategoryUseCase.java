package com.furkan.tradeport.usecase;

import com.furkan.tradeport.command.CreateCategoryCommand;
import com.furkan.tradeport.model.Category;

public interface CreateCategoryUseCase {
    Category create(CreateCategoryCommand command);
}
