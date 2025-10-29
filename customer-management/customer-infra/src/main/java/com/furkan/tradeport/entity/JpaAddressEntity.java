package com.furkan.tradeport.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "country", "city", "district", "street", "apartmentNumber", "doorNumber"
        })
})
public class JpaAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;
    private String city;
    private String district;
    private String street;
    private Integer apartmentNumber;
    private Integer doorNumber;

    public JpaAddressEntity() {
    }

    public JpaAddressEntity(String country, String city, String district, String street, Integer apartmentNumber, Integer doorNumber) {
        this.country = country;
        this.city = city;
        this.district = district;
        this.street = street;
        this.apartmentNumber = apartmentNumber;
        this.doorNumber = doorNumber;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setApartmentNumber(Integer apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setDoorNumber(Integer doorNumber) {
        this.doorNumber = doorNumber;
    }

    public Long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getStreet() {
        return street;
    }

    public Integer getApartmentNumber() {
        return apartmentNumber;
    }

    public Integer getDoorNumber() {
        return doorNumber;
    }
}
