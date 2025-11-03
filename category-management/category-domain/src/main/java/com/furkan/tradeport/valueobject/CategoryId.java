package com.furkan.tradeport.valueobject;

import java.util.UUID;

public final class CategoryId {

    private final String value;

    private CategoryId(String value) {
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
        return (o instanceof CategoryId) && ((CategoryId)o).value.equals(value);
    }

    @Override
    public int hashCode(){
        return value.hashCode();
    }
}
