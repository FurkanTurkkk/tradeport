package com.furkan.tradeport.command;

import java.util.Set;

public record RegisterUserCommand (String email, String rawPassword, Set<Integer> desiredRoles){}
