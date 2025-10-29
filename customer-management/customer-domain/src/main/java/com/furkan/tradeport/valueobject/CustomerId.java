package com.furkan.tradeport.valueobject;

import java.util.Objects;
import java.util.UUID;

public final class CustomerId {

    private final String value;

    private CustomerId(String value) {
        this.value = value;
    }

    public static CustomerId of(String value) {
        return new CustomerId(Objects.requireNonNull(value));
    }

    public static CustomerId random(){
        return new CustomerId(UUID.randomUUID().toString());
    }

    public String asString(){
        return value;
    }

    @Override
    public boolean equals(Object o){
        return (o instanceof CustomerId) && ((CustomerId)o).value.equals(value);
    }

    @Override public int hashCode(){
        return value.hashCode();
    }

}
