package com.furkan.tradeport.adapter;

import com.furkan.tradeport.config.JwtConfig;
import com.furkan.tradeport.model.User;
import com.furkan.tradeport.port.TokenGeneratorPort;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Component
public class JwtTokenProvider implements TokenGeneratorPort {

    private final String internalSecretKey;
    private final String secret;
    private final long expirationMs;

    public JwtTokenProvider(JwtConfig config) {
        this.secret = config.getSecret();
        this.internalSecretKey = config.getInternalSecretKey();
        this.expirationMs = config.getExpirationMs();
    }

    public String generateToken(User user) {
        String customerId = getCustomerId(user.getUserId().asString());

        JwtBuilder builder = Jwts.builder()
                .setSubject(user.getEmail().asString())
                .claim("roles", user.getRoles())
                .claim("userId", user.getUserId().asString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.HS256, secret);

        if (customerId != null && !customerId.isBlank()) {
            builder.claim("customerId", customerId);
        }

        return builder.compact();
    }

    private String getCustomerId(String userId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-INTERNAL-SECRET", internalSecretKey);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    "http://localhost:9090/api/v1/customer/find/" + userId,
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                String body = response.getBody();
                return (body != null && !body.isBlank()) ? body : null;
            }

        } catch (Exception e) {
            System.err.println(" CustomerId fetch failed for userId " + userId + ": " + e.getMessage());
        }

        return null;
    }
}
