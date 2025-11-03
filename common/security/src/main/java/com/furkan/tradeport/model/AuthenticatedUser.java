package com.furkan.tradeport.model;

import java.util.Set;

public record AuthenticatedUser(String userId, String customerId, Set<String> roles) {
}
