package com.furkan.tradeport.port;

import com.furkan.tradeport.model.Product;
import com.furkan.tradeport.valueobject.CategoryId;
import com.furkan.tradeport.valueobject.ProductCode;
import com.furkan.tradeport.valueobject.ProductId;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
    String save(Product product);
    Optional<Product> findById(ProductId productId);
    Optional<Product> findByCode(ProductCode productCode);
    List<Product> findAll();
    List<Product> findByCategory(CategoryId categoryId);
    void deleteById(ProductId productId);
}
