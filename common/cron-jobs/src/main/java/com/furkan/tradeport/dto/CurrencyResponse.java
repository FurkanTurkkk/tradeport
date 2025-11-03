package com.furkan.tradeport.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "Tarih_Date")
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrencyResponse {

    @XmlElement(name = "Currency")
    private List<CurrencyDto> currencies;

    public List<CurrencyDto> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyDto> currencies) {
        this.currencies = currencies;
    }
}
