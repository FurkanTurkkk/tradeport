package com.furkan.tradeport.port;

import com.furkan.tradeport.model.User;

public interface TokenGeneratorPort {
    String generateToken(User user);
}
