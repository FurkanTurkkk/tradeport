package com.furkan.tradeport.adapter;

import com.furkan.tradeport.entity.JpaUserEntity;
import com.furkan.tradeport.model.User;
import com.furkan.tradeport.persistence.SpringDataUserRepository;
import com.furkan.tradeport.port.ReadUserPort;
import com.furkan.tradeport.valueobject.EmailAddress;
import com.furkan.tradeport.valueobject.Role;
import com.furkan.tradeport.valueobject.UserId;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReadUserAdapter implements ReadUserPort {

    private final SpringDataUserRepository repository;

    public ReadUserAdapter(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> findById(UserId id) {
        return repository.findById(id.asString()).map(this::toDomain); // map(jpaUserEntity -> toDomain(jpaUserEntity))
    }

    @Override
    public Optional<User> findByEmail(EmailAddress email) {
        return repository.findByEmail(email.asString()).map(this::toDomain);
    }

    private User toDomain(JpaUserEntity e) {
        Set<Role> roles = Set.of(e.getRoles().split(",")).stream()
                .map(Role::valueOf).collect(Collectors.toSet());
        return new User(UserId.of(e.getId()), EmailAddress.of(e.getEmail()), e.getPasswordHash(), roles);
    }

}
