package com.furkan.tradeport.valueobject;

import java.util.Objects;
import java.util.UUID;

public final class VariantId {

    private final String value;

    private VariantId(String value) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("VariantId cannot be blank");
        this.value = value;
    }

    public static VariantId of(String value) {
        return new VariantId(value);
    }

    public static VariantId random() {
        return new VariantId(UUID.randomUUID().toString());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof VariantId that) && value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
