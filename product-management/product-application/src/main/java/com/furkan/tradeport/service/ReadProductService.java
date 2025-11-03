package com.furkan.tradeport.service;

import com.furkan.tradeport.exception.ProductNotFoundException;
import com.furkan.tradeport.model.Product;
import com.furkan.tradeport.port.ProductRepositoryPort;
import com.furkan.tradeport.usecase.ReadProductUseCase;
import com.furkan.tradeport.valueobject.CategoryId;
import com.furkan.tradeport.valueobject.ProductCode;
import com.furkan.tradeport.valueobject.ProductId;

import java.util.List;

public class ReadProductService implements ReadProductUseCase {
    private final ProductRepositoryPort  productRepositoryPort;

    public ReadProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product findByProductId(String productId) {
        return productRepositoryPort.findById(ProductId.of(productId))
                .orElseThrow(() -> new ProductNotFoundException("Product could not found by id : " + productId));
    }

    @Override
    public Product findByProductCode(String productCode) {
        return productRepositoryPort.findByCode(ProductCode.of(productCode))
                .orElseThrow(() -> new ProductNotFoundException("Product could not found by code : " + productCode));
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepositoryPort.findAll();
    }

    @Override
    public List<Product> findByCategory(CategoryId categoryId) {
        return productRepositoryPort.findByCategory(categoryId);
    }
}
