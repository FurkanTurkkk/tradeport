package com.furkan.tradeport.dto;

import java.util.Set;

public record RegisterRequest (String email, String password, Set<Integer> desiredRoles) {
}
