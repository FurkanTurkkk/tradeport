package com.furkan.tradeport.port;

import com.furkan.tradeport.model.User;
import com.furkan.tradeport.valueobject.EmailAddress;
import com.furkan.tradeport.valueobject.UserId;

import java.util.Optional;

public interface ReadUserPort {
    Optional<User> findById(UserId id);
    Optional<User> findByEmail(EmailAddress email);
}
