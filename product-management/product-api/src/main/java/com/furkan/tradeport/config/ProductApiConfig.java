package com.furkan.tradeport.config;

import com.furkan.tradeport.port.ProductRepositoryPort;
import com.furkan.tradeport.service.CreateProductService;
import com.furkan.tradeport.service.ReadProductService;
import com.furkan.tradeport.usecase.CreateProductUseCase;
import com.furkan.tradeport.usecase.ReadProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductApiConfig {

    @Bean
    public CreateProductUseCase createProductService(ProductRepositoryPort productRepositoryPort) {
        return new CreateProductService(productRepositoryPort);
    }

    @Bean
    public ReadProductUseCase readProductService(ProductRepositoryPort productRepositoryPort) {
        return new ReadProductService(productRepositoryPort);
    }
}
