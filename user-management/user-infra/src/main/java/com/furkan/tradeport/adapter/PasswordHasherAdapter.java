package com.furkan.tradeport.adapter;

import com.furkan.tradeport.port.PasswordHasherPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHasherAdapter implements PasswordHasherPort {

    private final PasswordEncoder encoder;

    public PasswordHasherAdapter(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public String hash(String raw) {
        return encoder.encode(raw);
    }

    @Override
    public boolean matches(String raw, String hashed) {
        return encoder.matches(raw, hashed);
    }
}
