package com.furkan.tradeport.valueobject;

import com.furkan.tradeport.exception.ProductDomainException;

import java.math.BigDecimal;
import java.util.Currency;

public final class Price {

    private final BigDecimal amount;
    private final Currency currency;

    private Price(BigDecimal amount, Currency currency) {
        if (amount == null || currency == null)
            throw new ProductDomainException("Amount and currency required");
        if (amount.scale() > 2)
            throw new ProductDomainException("Too many decimal places");
        if (amount.compareTo(BigDecimal.ZERO) < 0)
            throw new ProductDomainException("Amount cannot be negative");
        this.amount = amount.setScale(2);
        this.currency = currency;
    }

    public static Price of(BigDecimal amount, Currency currency) {
        return new Price(amount, currency);
    }

    public static Price zero(Currency currency) {
        return new Price(BigDecimal.ZERO, currency);
    }

    public Price add(Price other) {
        checkSameCurrency(other);
        return new Price(this.amount.add(other.amount), currency);
    }

    public Price subtract(Price other) {
        checkSameCurrency(other);
        return new Price(this.amount.subtract(other.amount), currency);
    }

    public boolean isLessThan(Price other) {
        checkSameCurrency(other);
        return this.amount.compareTo(other.amount) < 0;
    }

    private void checkSameCurrency(Price other) {
        if (!currency.equals(other.currency))
            throw new IllegalArgumentException("Currency mismatch");
    }

    public BigDecimal getAmount() { return amount; }
    public Currency getCurrency() { return currency; }

}
