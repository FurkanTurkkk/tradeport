package com.furkan.tradeport.usecase;

import com.furkan.tradeport.command.RegisterUserCommand;
import com.furkan.tradeport.valueobject.UserId;

public interface RegisterUserUseCase {
    UserId register(RegisterUserCommand registerUserCommand);
}
