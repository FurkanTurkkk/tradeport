package com.furkan.tradeport.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class JpaCustomerEntity {

    @Id
    private String customerId;
    private String userId;
    private String idNumber;
    private String firstname;
    private String lastname;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private JpaAddressEntity address;


    protected JpaCustomerEntity() {}

    public JpaCustomerEntity(String customerId, String userId) {
        this.customerId = customerId;
        this.userId = userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAddress(JpaAddressEntity address) {
        this.address = address;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getUserId() {
        return userId;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public JpaAddressEntity getAddress() {
        return address;
    }
}
