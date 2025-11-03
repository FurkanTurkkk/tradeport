package com.furkan.tradeport.dto.converter;

import com.furkan.tradeport.command.CreateProductCommand;
import com.furkan.tradeport.command.CreateProductVariantCommand;
import com.furkan.tradeport.dto.CreateProductRequest;
import com.furkan.tradeport.dto.ProductDto;
import com.furkan.tradeport.model.Product;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductDtoConverter {
    public CreateProductCommand toCreateProductCommand(CreateProductRequest request) {
        Set<CreateProductVariantCommand> variantCommands = request.variants().stream()
                .map(variant -> new CreateProductVariantCommand(
                        variant.attributeKey(),
                        variant.attributeValue()))
                .collect(Collectors.toSet());

        return new CreateProductCommand(
                request.categoryId(),
                request.price(),
                request.currency(),
                request.brand(),
                request.productCode(),
                request.productName(),
                request.barcode(),
                request.description(),
                variantCommands
        );
    }

    public ProductDto toProductDto(Product product) {
        return new ProductDto(
                product.getProductId().asString(),
                product.getCategoryId().getValue(),
                product.getPrice().getAmount(),
                product.getPrice().getCurrency().getCurrencyCode(),
                product.getBrand().getName(),
                product.getProductCode().getValue(),
                product.getProductName().getValue(),
                product.getBarcode().getValue(),
                product.getDescription()
        );
    }
}
