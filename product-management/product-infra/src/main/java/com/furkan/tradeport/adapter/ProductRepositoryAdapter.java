package com.furkan.tradeport.adapter;

import com.furkan.tradeport.entity.JpaProductEntity;
import com.furkan.tradeport.entity.JpaProductVariantEntity;
import com.furkan.tradeport.model.Product;
import com.furkan.tradeport.model.ProductVariant;
import com.furkan.tradeport.persistence.SpringProductRepository;
import com.furkan.tradeport.port.ProductRepositoryPort;
import com.furkan.tradeport.valueobject.*;
import org.springframework.stereotype.Service;

import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final SpringProductRepository productRepository;

    public ProductRepositoryAdapter(SpringProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String save(Product product) {
        JpaProductEntity productEntity = productRepository.save(toProductEntity(product));
        return productEntity.getId();
    }

    @Override
    public Optional<Product> findById(ProductId productId) {
        return productRepository.findById(productId.asString())
                .map(this::toProductDomain);
    }

    @Override
    public Optional<Product> findByCode(ProductCode productCode) {
        return productRepository.findByProductCode(productCode.getValue())
                .map(this::toProductDomain);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toProductDomain).collect(Collectors.toList());
    }

    @Override
    public List<Product> findByCategory(CategoryId categoryId) {
        return productRepository.findByCategoryId(categoryId.getValue())
                .stream()
                .map(this::toProductDomain).collect(Collectors.toList());
    }

    @Override
    public void deleteById(ProductId productId) {
        productRepository.deleteById(productId.asString());
    }

    private Product toProductDomain(JpaProductEntity entity) {
        Set<ProductVariant> variants = entity.getVariants().stream()
                .map(this::toProductVariantDomain)
                .collect(Collectors.toSet());

        return Product.create(
                ProductId.of(entity.getId()),
                CategoryId.of(entity.getCategoryId()),
                Price.of(entity.getPrice(), Currency.getInstance(entity.getCurrency())),
                Brand.of(entity.getBrand()),
                ProductCode.of(entity.getProductCode()),
                ProductName.of(entity.getProductName()),
                Barcode.of(entity.getBarcode()),
                entity.getDescription(),
                variants
        );
    }

    private ProductVariant toProductVariantDomain(JpaProductVariantEntity productVariantEntity) {
        return new ProductVariant(VariantId.of(
                productVariantEntity.getId()),
                productVariantEntity.getAttributeKey(),
                productVariantEntity.getAttributeValue());
    }

    private JpaProductEntity toProductEntity(Product product) {
        JpaProductEntity productEntity = new JpaProductEntity(
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

        Set<JpaProductVariantEntity> variantEntities = product.getProductVariants().stream()
                .map(this::toProductVariantEntity)
                .collect(Collectors.toSet());

        variantEntities.forEach(v -> v.setProduct(productEntity));

        productEntity.setVariants(variantEntities);
        return productEntity;
    }

    private JpaProductVariantEntity toProductVariantEntity(ProductVariant variant) {
        return new JpaProductVariantEntity(variant.getId().getValue(),
                variant.getKey(),
                variant.getValue()
        );
    }
}
