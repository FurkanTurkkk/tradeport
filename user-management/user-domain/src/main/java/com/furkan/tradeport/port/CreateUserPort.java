package com.furkan.tradeport.port;

import com.furkan.tradeport.model.User;

public interface CreateUserPort {
    User save(User user);
}
