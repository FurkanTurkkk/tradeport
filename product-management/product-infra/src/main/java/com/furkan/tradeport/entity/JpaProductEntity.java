package com.furkan.tradeport.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class JpaProductEntity {

    @Id
    private String id;
    private String categoryId;
    private BigDecimal price;
    private String currency;
    private String brand;
    private String productCode;
    private String productName;
    private String barcode;
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JpaProductVariantEntity> variants = new HashSet<>();

    public JpaProductEntity() {}

    public JpaProductEntity(String id,
                            String categoryId,
                            BigDecimal price,
                            String currency,
                            String brand,
                            String productCode,
                            String productName,
                            String barcode,
                            String description) {
        this.id = id;
        this.categoryId = categoryId;
        this.price = price;
        this.currency = currency;
        this.brand = brand;
        this.productCode = productCode;
        this.productName = productName;
        this.barcode = barcode;
        this.description = description;
    }

    public void setVariants(Set<JpaProductVariantEntity> variants) {
        this.variants = variants;
    }

    public String getId() {
        return id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getBrand() {
        return brand;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getDescription() {
        return description;
    }

    public Set<JpaProductVariantEntity> getVariants() {
        return variants;
    }

}
