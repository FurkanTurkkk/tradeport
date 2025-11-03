package com.furkan.tradeport.usecase;

import com.furkan.tradeport.model.Product;
import com.furkan.tradeport.valueobject.CategoryId;

import java.util.List;

public interface ReadProductUseCase {
    Product findByProductId(String productId);
    Product findByProductCode(String productCode);
    List<Product> findAllProducts();
    List<Product> findByCategory(CategoryId categoryId);
}
