package com.furkan.tradeport.adapter;

import com.furkan.tradeport.entity.JpaUserEntity;
import com.furkan.tradeport.model.User;
import com.furkan.tradeport.persistence.SpringDataUserRepository;
import com.furkan.tradeport.port.DeleteUserPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeleteUserAdapter implements DeleteUserPort {

    private final SpringDataUserRepository repository;

    public DeleteUserAdapter(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public String delete(User user) {
        Optional<JpaUserEntity> userEntity = repository.findById(user.getUserId().asString());
        userEntity.ifPresent(repository::delete);
        return "Kullanıcı başarıyla silindi";
    }
}
