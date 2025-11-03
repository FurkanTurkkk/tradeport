package com.furkan.tradeport.valueobject;

import java.util.Objects;

public final class ProductName {

    private final String value;

    private ProductName(String value) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("Product name cannot be blank");
        if (value.length() < 2 || value.length() > 255)
            throw new IllegalArgumentException("Product name must be 2–255 characters");
        this.value = value.trim();
    }

    public static ProductName of(String value) {
        return new ProductName(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof ProductName that) && value.equalsIgnoreCase(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value.toLowerCase());
    }
}
