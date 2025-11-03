package com.furkan.tradeport.valueobject;

import com.furkan.tradeport.exception.ProductDomainException;

import java.util.UUID;

public final class ProductId {

    private final String value;

    private ProductId(String value) {
        if(value == null || value.isEmpty()) {
            throw new ProductDomainException("Product id must not be null or empty");
        }
        this.value = value;
    }

    public static ProductId of(String productId) {
        return new ProductId(productId);
    }

    public static ProductId random() {
        return new ProductId(UUID.randomUUID().toString());
    }

    public String asString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductId productId)) return false;
        return value.equals(productId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
