package com.furkan.tradeport.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class JpaCustomerEntity {

    @Id
    private String customerId;

    private String userId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private JpaAddressEntity address;

    protected JpaCustomerEntity() {}

    public JpaCustomerEntity(String customerId, String userId) {
        this.customerId = customerId;
        this.userId = userId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getUserId() {
        return userId;
    }

    public JpaAddressEntity getAddress() {
        return address;
    }

    public void setAddress(JpaAddressEntity address) {
        this.address = address;
    }
}
