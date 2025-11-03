package com.furkan.tradeport.valueobject;

import java.util.Objects;
import java.util.regex.Pattern;

public final class ProductCode {

    private static final Pattern CODE_PATTERN = Pattern.compile("^[A-Z0-9_-]{3,64}$");

    private final String value;

    private ProductCode(String value) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("ProductCode cannot be blank");
        if (!CODE_PATTERN.matcher(value).matches())
            throw new IllegalArgumentException("Invalid product code format");
        this.value = value;
    }

    public static ProductCode of(String value) {
        return new ProductCode(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof ProductCode that) && value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
