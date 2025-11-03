package com.furkan.tradeport.valueobject;

import java.util.regex.Pattern;

public final class Barcode {

    private static final Pattern PATTERN = Pattern.compile("^\\d{8,14}$");

    private final String value;

    private Barcode(String value) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("Barcode cannot be blank");
        if (!PATTERN.matcher(value).matches())
            throw new IllegalArgumentException("Invalid barcode format");
        this.value = value;
    }

    public static Barcode of(String value) {
        return new Barcode(value);
    }

    public String getValue() { return value; }
}
