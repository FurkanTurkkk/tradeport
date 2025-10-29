package com.furkan.tradeport.service;

import com.furkan.tradeport.exception.InvalidEmailException;
import com.furkan.tradeport.exception.InvalidPasswordException;
import com.furkan.tradeport.command.LoginUserCommand;
import com.furkan.tradeport.model.User;
import com.furkan.tradeport.port.PasswordHasherPort;
import com.furkan.tradeport.port.ReadUserPort;
import com.furkan.tradeport.port.TokenGeneratorPort;
import com.furkan.tradeport.usecase.LoginUserUseCase;
import com.furkan.tradeport.valueobject.EmailAddress;

public class LoginUserService implements LoginUserUseCase {

    private final ReadUserPort readUserPort;
    private final PasswordHasherPort passwordHasher;
    private final TokenGeneratorPort tokenGenerator;

    public LoginUserService(ReadUserPort readUserPort,
                            PasswordHasherPort passwordHasher,
                            TokenGeneratorPort tokenGenerator) {
        this.readUserPort = readUserPort;
        this.passwordHasher = passwordHasher;
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public String login(LoginUserCommand loginUserCommand) {
        EmailAddress emailObject = EmailAddress.of(loginUserCommand.email());
        User user = readUserPort.findByEmail(emailObject)
                .orElseThrow(() -> new InvalidEmailException("Böyle bir email adresi bulunamadı : "+ loginUserCommand.email()));

        if (!passwordHasher.matches(loginUserCommand.password(), user.getPasswordHash())) {
            throw new InvalidPasswordException("Şifrenizi tekrar kontrol ediniz");
        }

        return tokenGenerator.generateToken(user);
    }
}
