package com.furkan.tradeport.valueobject;

import java.util.Objects;
import java.util.UUID;

public final class CategoryId {

    private final String value;

    private CategoryId(String value) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("CategoryId cannot be blank");
        this.value = value;
    }

    public static CategoryId of(String value) {
        return new CategoryId(value);
    }

    public static CategoryId random() {
        return new CategoryId(UUID.randomUUID().toString());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof CategoryId that) && value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
