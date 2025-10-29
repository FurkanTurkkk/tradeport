package com.furkan.tradeport.valueobject;

import java.util.Objects;

public final class IdNumber {

    private final String value;

    public IdNumber(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("TC kimlik numarası boş olamaz");
        }
        if (!value.matches("\\d{11}")) {
            throw new IllegalArgumentException("TC kimlik numarası 11 haneli olmalı ve sadece rakam içermelidir");
        }
        if (value.charAt(0) == '0') {
            throw new IllegalArgumentException("TC kimlik numarası 0 ile başlayamaz");
        }
        if (!isValidIdNumber(value)) {
            throw new IllegalArgumentException("Geçersiz TC kimlik numarası");
        }

        this.value = value;
    }

    public String asString() {
        return value;
    }

    private static boolean isValidIdNumber(String value) {
        int[] digits = value.chars().map(c -> c - '0').toArray();
        int oddSum = digits[0] + digits[2] + digits[4] + digits[6] + digits[8];
        int evenSum = digits[1] + digits[3] + digits[5] + digits[7];
        int digit10 = ((oddSum * 7) - evenSum) % 10;
        int digit11 = (oddSum + evenSum + digits[9]) % 10;
        return digits[9] == digit10 && digits[10] == digit11;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdNumber)) return false;
        IdNumber tckn = (IdNumber) o;
        return value.equals(tckn.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Tckn{value='*********" + value.substring(value.length() - 3) + "'}";
    }
}
