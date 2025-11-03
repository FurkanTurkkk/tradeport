package com.furkan.tradeport.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class CurrencyDto {

    @XmlAttribute(name = "Kod")
    private String code;

    @XmlElement(name = "ForexBuying")
    private BigDecimal buying;

    @XmlElement(name = "ForexSelling")
    private BigDecimal selling;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getBuying() {
        return buying;
    }

    public void setBuying(BigDecimal buying) {
        this.buying = buying;
    }

    public BigDecimal getSelling() {
        return selling;
    }

    public void setSelling(BigDecimal selling) {
        this.selling = selling;
    }
}
