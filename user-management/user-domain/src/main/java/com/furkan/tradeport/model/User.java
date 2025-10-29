package com.furkan.tradeport.model;

import com.furkan.tradeport.valueobject.EmailAddress;
import com.furkan.tradeport.valueobject.Role;
import com.furkan.tradeport.valueobject.UserId;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {

    private final UserId userId;
    private EmailAddress emailAddress;
    private String passwordHash;
    private final Set<Role> roles = new HashSet<>();
    private final Instant createdAt;

    public User(UserId userId, EmailAddress emailAddress, String passwordHash, Set<Role> roles) {
        this.userId = userId;
        this.emailAddress = emailAddress;
        this.passwordHash = passwordHash;
        this.roles.addAll(roles);
        this.createdAt = Instant.now();
    }

    public UserId getUserId(){ return userId; }
    public EmailAddress getEmail(){ return emailAddress; }
    public String getPasswordHash() { return passwordHash; }
    public Set<Role> getRoles(){ return Collections.unmodifiableSet(roles); }
    public Instant getCreatedAt(){ return createdAt; }

    public boolean checkPasswordHashMatch(String candidateHash) { return this.passwordHash.equals(candidateHash); }

    public void changePasswordHash(String newHash) { this.passwordHash = Objects.requireNonNull(newHash); }

    public void changeEmail(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void addRole(Role r){
        roles.add(r);
    }

}
