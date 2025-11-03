package com.furkan.tradeport.service;

import com.furkan.tradeport.command.CreateProductCommand;
import com.furkan.tradeport.exception.ProductAlreadyExistException;
import com.furkan.tradeport.model.Product;
import com.furkan.tradeport.model.ProductVariant;
import com.furkan.tradeport.port.ProductRepositoryPort;
import com.furkan.tradeport.usecase.CreateProductUseCase;
import com.furkan.tradeport.valueobject.*;

import java.util.Currency;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class CreateProductService implements CreateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public CreateProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public String create(CreateProductCommand command) {

        ensureProductDoesNotExist(command.productCode());

        Product product = buildProduct(command);

        return productRepositoryPort.save(product);
    }

    private void ensureProductDoesNotExist(String productCode) {
        boolean exists = productRepositoryPort
                .findByCode(ProductCode.of(productCode))
                .isPresent();

        if (exists) {
            throw new ProductAlreadyExistException("Product with code already exists: " + productCode);
        }
    }

    private Product buildProduct(CreateProductCommand command) {

        return Product.create(
                ProductId.random(),
                CategoryId.of(command.categoryId()),
                Price.of(command.price(), Currency.getInstance(command.currency())),
                Brand.of(command.brand()),
                ProductCode.of(command.productCode()),
                ProductName.of(command.productName()),
                Barcode.of(command.barcode()),
                command.description(),
                buildVariants(command)
        );
    }

    private Set<ProductVariant> buildVariants(CreateProductCommand command) {
        return command.variants().stream()
                .map(v ->
                        new ProductVariant(VariantId.random(), v.attributeKey(), v.attributeValue()))
                .collect(Collectors.toSet());
    }
}
