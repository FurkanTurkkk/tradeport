package com.furkan.tradeport.valueobject;

import java.util.Objects;
import java.util.UUID;

public final class UserId {

    private final String value;

    private UserId(String value) {
        this.value = Objects.requireNonNull(value, "UserId değeri null olamaz");
    }

    public static UserId of(String value) {
        return new UserId(value);
    }

    public static UserId random() {
        return new UserId(UUID.randomUUID().toString());
    }

    public String asString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserId userId)) return false;
        return value.equals(userId.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
