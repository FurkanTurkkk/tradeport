package com.furkan.tradeport.config;

import com.furkan.tradeport.port.CategoryRepositoryPort;
import com.furkan.tradeport.service.CreateCategoryService;
import com.furkan.tradeport.service.DeleteCategoryService;
import com.furkan.tradeport.service.ReadCategoryService;
import com.furkan.tradeport.service.UpdateCategoryService;
import com.furkan.tradeport.usecase.CreateCategoryUseCase;
import com.furkan.tradeport.usecase.DeleteCategoryUseCase;
import com.furkan.tradeport.usecase.ReadCategoryUseCase;
import com.furkan.tradeport.usecase.UpdateCategoryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryConfig {

    @Bean
    public CreateCategoryUseCase createCategoryUseCase(CategoryRepositoryPort categoryRepositoryPort) {
        return new CreateCategoryService(categoryRepositoryPort);
    }

    @Bean
    public ReadCategoryUseCase readCategoryUseCase(CategoryRepositoryPort categoryRepositoryPort) {
        return new ReadCategoryService(categoryRepositoryPort);
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase(CategoryRepositoryPort categoryRepositoryPort) {
        return new UpdateCategoryService(categoryRepositoryPort);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase(CategoryRepositoryPort categoryRepositoryPort) {
        return new DeleteCategoryService(categoryRepositoryPort);
    }
}
