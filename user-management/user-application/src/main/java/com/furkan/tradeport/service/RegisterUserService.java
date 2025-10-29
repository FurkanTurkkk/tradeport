package com.furkan.tradeport.service;

import com.furkan.tradeport.exception.EmailAlreadyExistException;
import com.furkan.tradeport.command.RegisterUserCommand;
import com.furkan.tradeport.event.UserRegisteredEvent;
import com.furkan.tradeport.model.User;
import com.furkan.tradeport.port.CreateUserPort;
import com.furkan.tradeport.port.EventPublisherPort;
import com.furkan.tradeport.port.PasswordHasherPort;
import com.furkan.tradeport.port.ReadUserPort;
import com.furkan.tradeport.usecase.RegisterUserUseCase;
import com.furkan.tradeport.valueobject.EmailAddress;
import com.furkan.tradeport.valueobject.Role;
import com.furkan.tradeport.valueobject.UserId;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class RegisterUserService implements RegisterUserUseCase {

    private final CreateUserPort createUserPort;
    private final ReadUserPort readUserPort;
    private final PasswordHasherPort passwordHasherPort;
    private final EventPublisherPort eventPublisherPort;

    public RegisterUserService(CreateUserPort createUserPort, ReadUserPort readUserPort, PasswordHasherPort passwordHasherPort, EventPublisherPort eventPublisherPort) {
        this.createUserPort = createUserPort;
        this.readUserPort = readUserPort;
        this.passwordHasherPort = passwordHasherPort;
        this.eventPublisherPort = eventPublisherPort;
    }

    @Override
    public UserId register(RegisterUserCommand registerUserCommand) {
        EmailAddress email = EmailAddress.of(registerUserCommand.email());
        Optional<User> existingUser = readUserPort.findByEmail(email);
        if(existingUser.isPresent()) {
            throw new EmailAlreadyExistException("Email already exists");
        }
        User createdUser = createUser(registerUserCommand, email);
        User savedUser = createUserPort.save(createdUser);
        eventPublisherPort.publish(new UserRegisteredEvent(savedUser.getUserId().asString(),savedUser.getEmail().asString()));
        // TODO After register event , create customer with RabbitMQ
        return savedUser.getUserId();
    }

    private User createUser(RegisterUserCommand registerUserCommand, EmailAddress email) {
        UserId userId = UserId.random();
        String passwordHash = passwordHasherPort.hash(registerUserCommand.rawPassword());
        Set<Role> roles = registerUserCommand.desiredRoles().stream()
                .map(role -> Role.valueOf(Role.getRoleByCode(role)))
                .collect(Collectors.toSet());
        return new User(userId,email,passwordHash,roles);
    }
}
