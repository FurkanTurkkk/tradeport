package com.furkan.tradeport.dto;

public record UpdateCustomerRequest(String firstname, String lastname, String idNumber, String country, String city,
                                    String district, String street, Integer apartmentNumber, Integer doorNumber) {
}
