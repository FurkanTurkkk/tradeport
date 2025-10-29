package com.furkan.tradeport.aspect;

import com.furkan.tradeport.annotation.RequireRoles;
import com.furkan.tradeport.model.AuthenticatedUser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Aspect
@Component
public class AuthorizationAspect {

    @Around("@annotation(requireRoles)")
    public Object checkRoles(ProceedingJoinPoint joinPoint, RequireRoles requireRoles) throws Throwable {

        // 🔹 SecurityContext'ten Authentication al
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User not authenticated");
        }

        // 🔹 Principal'ı senin tipine cast et
        var principal = authentication.getPrincipal();
        if (!(principal instanceof AuthenticatedUser user)) {
            throw new RuntimeException("Invalid user principal type");
        }

        // 🔹 Rol kontrolü
        Set<String> roles = user.roles();
        Set<String> required = Set.of(requireRoles.value());

        boolean allowed = roles.stream().anyMatch(required::contains);
        if (!allowed) {
            throw new RuntimeException("Access denied");
        }

        // 🔹 Devam et
        return joinPoint.proceed();
    }
}
