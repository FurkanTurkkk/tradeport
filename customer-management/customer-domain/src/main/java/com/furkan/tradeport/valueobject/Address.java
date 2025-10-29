package com.furkan.tradeport.valueobject;

import java.util.Objects;

public final class Address {

    private final String country;
    private final String city;
    private final String district;
    private final String street;
    private final Integer apartmentNumber;
    private final Integer doorNumber;

    public Address(String country,
                   String city,
                   String district,
                   String street,
                   Integer apartmentNumber,
                   Integer doorNumber) {

        if (isBlank(country)) {
            throw new IllegalArgumentException("Ülke bilgisi boş olamaz");
        }
        if (isBlank(city)) {
            throw new IllegalArgumentException("Şehir bilgisi boş olamaz");
        }
        if (isBlank(district)) {
            throw new IllegalArgumentException("İlçe bilgisi boş olamaz");
        }
        if(apartmentNumber == null || apartmentNumber <= 0) {
            throw new IllegalArgumentException("Apartman numarası boş olamaz");
        }
        if(doorNumber == null || doorNumber <= 0) {
            throw new IllegalArgumentException("Kapı numarası boş olamaz");
        }

        this.country = country.trim();
        this.city = city.trim();
        this.district = district.trim();
        this.street = street != null ? street.trim() : "";
        this.apartmentNumber = apartmentNumber;
        this.doorNumber = doorNumber;
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    public String country() {
        return country;
    }

    public String city() {
        return city;
    }

    public String district() {
        return district;
    }

    public String street() {
        return street;
    }

    public Integer apartmentNumber() { return apartmentNumber; }

    public Integer doorNumber() { return doorNumber; }

    /**
     * İki adresin eşit kabul edilmesi için tüm alanlarının aynı olması gerekir.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return country.equalsIgnoreCase(address.country) &&
                city.equalsIgnoreCase(address.city) &&
                district.equalsIgnoreCase(address.district) &&
                street.equalsIgnoreCase(address.street) &&
                apartmentNumber.equals(address.apartmentNumber) &&
                doorNumber.equals(address.doorNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country.toLowerCase(), city.toLowerCase(), district.toLowerCase(),
                street.toLowerCase(), apartmentNumber, doorNumber);
    }

    @Override
    public String toString() {
        return "%s, %s / %s, %s (%s)".formatted(district, city, country, street, apartmentNumber);
    }
}
