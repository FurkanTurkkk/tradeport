package com.furkan.tradeport.usecase;

import com.furkan.tradeport.command.LoginUserCommand;

public interface LoginUserUseCase {
    String login(LoginUserCommand loginUserCommand);
}
