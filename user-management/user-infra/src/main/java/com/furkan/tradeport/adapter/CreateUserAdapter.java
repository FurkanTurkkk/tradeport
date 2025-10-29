package com.furkan.tradeport.adapter;

import com.furkan.tradeport.entity.JpaUserEntity;
import com.furkan.tradeport.model.User;
import com.furkan.tradeport.persistence.SpringDataUserRepository;
import com.furkan.tradeport.port.CreateUserPort;
import com.furkan.tradeport.valueobject.EmailAddress;
import com.furkan.tradeport.valueobject.Role;
import com.furkan.tradeport.valueobject.UserId;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreateUserAdapter implements CreateUserPort {

    private final SpringDataUserRepository repository;

    public CreateUserAdapter(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        String roleCsv = user.getRoles().stream().map(Enum::name).collect(Collectors.joining(","));
        JpaUserEntity entity = new JpaUserEntity(
                user.getUserId().asString(),
                user.getEmail().asString(),
                user.getPasswordHash(),
                roleCsv,
                user.getCreatedAt()
        );
        JpaUserEntity savedUser = repository.save(entity);
        return toDomain(savedUser);
    }

    private User toDomain(JpaUserEntity savedUser) {
        Set<Role> roles = Set.of(savedUser.getRoles().split(",")).stream()
                .map(Role::valueOf).collect(Collectors.toSet());
        return new User(UserId.of(savedUser.getId()),
                EmailAddress.of(savedUser.getEmail()),
                savedUser.getPasswordHash(),
                roles
        );
    }
}
