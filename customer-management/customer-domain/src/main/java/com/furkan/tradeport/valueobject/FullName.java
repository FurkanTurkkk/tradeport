package com.furkan.tradeport.valueobject;

import java.text.Normalizer;
import java.util.Objects;

public final class FullName {

    private static final int MAX_LENGTH = 50;
    private static final String NAME_PATTERN = "^[A-Za-zÇĞİÖŞÜçğıöşü\\s'-]+$";

    private final String firstName;
    private final String lastName;

    public FullName(String firstName, String lastName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("İsim alanı boş olamaz");
        }

        String normalizedFirst = normalize(firstName);
        String normalizedLast = normalize(lastName);

        if (!normalizedFirst.matches(NAME_PATTERN)) {
            throw new IllegalArgumentException("İsim yalnızca harf içerebilir");
        }
        if (!normalizedLast.matches(NAME_PATTERN)) {
            throw new IllegalArgumentException("Soyisim yalnızca harf içerebilir");
        }

        if (normalizedFirst.length() > MAX_LENGTH || normalizedLast.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("İsim veya soyisim çok uzun");
        }

        this.firstName = capitalize(normalizedFirst);
        this.lastName = capitalize(normalizedLast);
    }

    private static String normalize(String input) {
        // Fazla boşlukları temizle, Unicode normalize et
        return Normalizer.normalize(input.trim(), Normalizer.Form.NFC);
    }

    private static String capitalize(String name) {
        if (name.isEmpty()) return name;
        // "mehmet" -> "Mehmet"
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public String fullName() {
        return firstName + " " + lastName;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullName fullName = (FullName) o;
        return firstName.equals(fullName.firstName) && lastName.equals(fullName.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return fullName();
    }
}
