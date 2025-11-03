package com.furkan.tradeport.model;

import com.furkan.tradeport.valueobject.Address;
import com.furkan.tradeport.valueobject.CustomerId;
import com.furkan.tradeport.valueobject.FullName;
import com.furkan.tradeport.valueobject.IdNumber;

public class Customer {

    private final CustomerId customerId;
    private FullName fullname;
    private IdNumber idNumber;
    private Address address;

    public Customer(CustomerId customerId, FullName fullname, IdNumber idNumber, Address address) {
        this.customerId = customerId;
        this.fullname = fullname;
        this.idNumber = idNumber;
        this.address = address;
    }

    public Customer(CustomerId customerId) {
        this.customerId = customerId;
        this.fullname = null;
        this.idNumber = null;
        this.address = null;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public FullName getFullname() {
        return fullname;
    }

    public IdNumber getIdNumber() {
        return idNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void changeAddress(Address address) {
        this.address = address;
    }

    public void changeIdNumber(IdNumber idNumber) {
        this.idNumber = idNumber;
    }

    public void changeFullName(FullName fullname) {
        this.fullname = fullname;
    }
}
