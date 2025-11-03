package com.furkan.tradeport.model;

import com.furkan.tradeport.valueobject.VariantId;

public class ProductVariant {

    private VariantId id;
    private String key;
    private String value;

    public ProductVariant(VariantId id, String key, String value) {
        if (key == null || key.isBlank())
            throw new IllegalArgumentException("Variant key cannot be empty");
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("Variant value cannot be empty");

        this.id = id;
        this.key = key;
        this.value = value;
    }

    public VariantId getId() { return id; }
    public String getKey() { return key; }
    public String getValue() { return value; }

    public void changeValue(String newValue) {
        if (newValue == null || newValue.isBlank())
            throw new IllegalArgumentException("Variant value cannot be empty");
        this.value = newValue;
    }
}
