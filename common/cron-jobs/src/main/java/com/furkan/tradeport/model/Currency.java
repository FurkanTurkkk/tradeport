package com.furkan.tradeport.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private BigDecimal buying;
    private BigDecimal selling;

    private LocalDateTime fetchedAt; // ne zaman alındığını tutmak için

    public Long getId() {
        return id;
    }

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

    public LocalDateTime getFetchedAt() {
        return fetchedAt;
    }

    public void setFetchedAt(LocalDateTime fetchedAt) {
        this.fetchedAt = fetchedAt;
    }
}
