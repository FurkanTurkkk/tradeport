package com.furkan.tradeport.validator;

import com.furkan.tradeport.config.JwtConfig;
import com.furkan.tradeport.model.AuthenticatedUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class JwtTokenValidator {

    private final String secret;

    public JwtTokenValidator(JwtConfig config) {
        this.secret = config.getSecret();
    }

    public AuthenticatedUser validate(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            Set<String> roles = Set.copyOf(claims.get("roles", List.class));
            String userId = claims.get("userId", String.class);
            String customerId = claims.get("customerId", String.class);
            return new AuthenticatedUser(userId,customerId,roles);

        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT token");
        }
    }
}
