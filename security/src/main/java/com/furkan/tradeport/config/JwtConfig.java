package com.furkan.tradeport.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${internal.secret.key}")
    private String internalSecretKey;

    @Value("${security.jwt.expiration-ms}")
    private long expirationMs;

    public String getSecret() {
        return secret;
    }

    public String getInternalSecretKey() {
        return internalSecretKey;
    }

    public long getExpirationMs() {
        return expirationMs;
    }
}
