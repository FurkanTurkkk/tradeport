package com.furkan.tradeport.usecase;

import com.furkan.tradeport.command.CreateProductCommand;

public interface CreateProductUseCase {
    String create(CreateProductCommand command);
}
