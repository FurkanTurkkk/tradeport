package com.furkan.tradeport.port;

public interface PasswordHasherPort {
    String hash(String raw);
    boolean matches(String raw, String hashed);
}
