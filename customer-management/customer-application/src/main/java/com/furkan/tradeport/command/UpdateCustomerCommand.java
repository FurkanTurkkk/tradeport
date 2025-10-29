package com.furkan.tradeport.command;

public record UpdateCustomerCommand (String firstname, String lastname, String idNumber, String country, String city,
                                     String district, String street, Integer apartmentNumber, Integer doorNumber){}
