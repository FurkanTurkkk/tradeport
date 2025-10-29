package com.furkan.tradeport.valueobject;

import com.furkan.tradeport.exception.InvalidEmailException;

import java.util.Objects;
import java.util.regex.Pattern;

public final class EmailAddress {

    private static final String EMAIL_REGEX =
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

    private final String value;

    private EmailAddress(String value) {
        this.value = value;
    }

    /**
     * Email oluşturmak için factory method.
     * Geçersiz formatta ise özel exception fırlatır.
     */
    public static EmailAddress of(String value) {
        validate(value);
        return new EmailAddress(value.trim());
    }

    private static void validate(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidEmailException("Email adresi boş olamaz.");
        }

        if (value.length() > 320) {
            throw new InvalidEmailException("Email adresi çok uzun (320 karakter sınırı).");
        }

        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new InvalidEmailException("Geçersiz email formatı: " + value);
        }
    }

    public String asString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailAddress email)) return false;
        return value.equalsIgnoreCase(email.value); // küçük/büyük fark etmesin
    }

    @Override
    public int hashCode() {
        return Objects.hash(value.toLowerCase());
    }

    @Override
    public String toString() {
        return value;
    }
}
