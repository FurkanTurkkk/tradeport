package com.furkan.tradeport.valueobject;

import com.furkan.tradeport.exception.CategoryDomainException;

import java.text.Normalizer;
import java.util.UUID;

public final class CategoryName {

    private static final String NAME_PATTERN = "^[A-Za-zÇĞİÖŞÜçğıöşü\\s'-]+$";
    private final String value;

    private CategoryName(String value) {
        if (!normalize(value).matches(NAME_PATTERN)) {
            throw new CategoryDomainException("İsim yalnızca harf içerebilir");
        }
        this.value = value;
    }

    public static CategoryName of(String value) {
        return new CategoryName(value);
    }

    public static CategoryName random() {
        return new CategoryName(UUID.randomUUID().toString());
    }

    private static String normalize(String input) {
        return Normalizer.normalize(input.trim(), Normalizer.Form.NFC);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof CategoryName) && ((CategoryName)o).value.equals(value);
    }

    @Override
    public int hashCode(){
        return value.hashCode();
    }
}
