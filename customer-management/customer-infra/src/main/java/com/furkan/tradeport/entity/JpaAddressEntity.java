package com.furkan.tradeport.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
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

    public JpaAddressEntity(String country, String city, String district, String street, Integer apartmentNumber, Integer doorNumber) {
        this.country = country;
        this.city = city;
        this.district = district;
        this.street = street;
        this.apartmentNumber = apartmentNumber;
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
