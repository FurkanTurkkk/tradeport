package com.furkan.tradeport.valueobject;

import java.util.Objects;

public final class Brand {

    private final String name;

    private Brand(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Brand name cannot be blank");
        if (name.length() > 255)
            throw new IllegalArgumentException("Brand name too long");
        this.name = name.trim();
    }

    public static Brand of(String name) {
        return new Brand(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Brand that) && name.equalsIgnoreCase(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }
}
