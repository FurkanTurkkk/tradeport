package com.furkan.tradeport.model;

import com.furkan.tradeport.valueobject.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Product {
    private final ProductId productId;
    private CategoryId categoryId;
    private Price price;
    private Brand brand;
    private final ProductCode productCode;
    private ProductName productName;
    private final Barcode barcode;
    private String description;
    private final Set<ProductVariant> productVariants = new HashSet<>();
    private boolean isActive;
    private final LocalDate createdAt;
    private LocalDate updatedAt;

    private Product(ProductId productId,
                    CategoryId categoryId,
                    Price price,
                    Brand brand,
                    ProductCode productCode,
                    ProductName productName,
                    Barcode barcode,
                    String description,
                    Set<ProductVariant> productVariants,
                    boolean isActive) {

        this.productId = productId;
        this.categoryId = categoryId;
        this.price = price;
        this.brand = brand;
        this.productCode = productCode;
        this.productName = productName;
        this.barcode = barcode;
        this.description = description;
        this.productVariants.addAll(productVariants);
        this.isActive = isActive;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public static Product create(ProductId productId,
                                 CategoryId categoryId,
                                 Price price,
                                 Brand brand,
                                 ProductCode productCode,
                                 ProductName productName,
                                 Barcode barcode,
                                 String description,
                                 Set<ProductVariant> productVariants){
        return new Product(
                productId,
                categoryId,
                price,
                brand,
                productCode,
                productName,
                barcode,
                description,
                productVariants,
                true
        );
    }

    public ProductId getProductId() {
        return productId;
    }
    public CategoryId getCategoryId() {
        return categoryId;
    }

    public Price getPrice() {
        return price;
    }

    public ProductCode getProductCode() {
        return productCode;
    }

    public Barcode getBarcode() {
        return barcode;
    }

    public String getDescription() {
        return description;
    }

    public Set<ProductVariant> getProductVariants() {
        return productVariants;
    }

    public boolean isActive() {
        return isActive;
    }

    public Brand getBrand() {
        return brand;
    }

    public ProductName getProductName() {
        return productName;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void changeState(boolean isActive) {
        this.isActive = isActive;
    }

    public void changePrice(Price newPrice) {
        Objects.requireNonNull(newPrice);
        if (newPrice.isLessThan(Price.zero(newPrice.getCurrency()))) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = newPrice;
        this.updatedAt = LocalDate.now();
    }

    public void changeCategory(CategoryId categoryId) {
        this.categoryId = categoryId;
        this.updatedAt = LocalDate.now();
    }

    public void changeBrand(Brand brand) {
        this.brand = brand;
        this.updatedAt = LocalDate.now();
    }

    public void rename(ProductName newName) {
        this.productName = newName;
        updatedAt = LocalDate.now();
    }

    public void changeDescription(String description) {
        this.description = description;
        updatedAt = LocalDate.now();
    }

    public void addVariant(ProductVariant productVariant) {
        this.productVariants.add(productVariant);
        updatedAt = LocalDate.now();
    }

    public void removeVariant(ProductVariant productVariant) {
        this.productVariants.remove(productVariant);
        updatedAt = LocalDate.now();
    }

}
