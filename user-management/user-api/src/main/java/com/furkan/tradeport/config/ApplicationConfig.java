package com.furkan.tradeport.config;

import com.furkan.tradeport.port.*;
import com.furkan.tradeport.service.DeleteUserService;
import com.furkan.tradeport.service.LoginUserService;
import com.furkan.tradeport.service.RegisterUserService;
import com.furkan.tradeport.usecase.DeleteUserUseCase;
import com.furkan.tradeport.usecase.LoginUserUseCase;
import com.furkan.tradeport.usecase.RegisterUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public RegisterUserUseCase registerUserUseCase(CreateUserPort createUserPort,
                                                   ReadUserPort readUserPort,
                                                   PasswordHasherPort passwordHasherPort,
                                                   EventPublisherPort  eventPublisherPort) {
        return new RegisterUserService(createUserPort,readUserPort,passwordHasherPort,eventPublisherPort);
    }

    @Bean
    public LoginUserUseCase loginUserUseCase(ReadUserPort readUserPort,
                                             PasswordHasherPort passwordHasher,
                                             TokenGeneratorPort tokenGenerator){
        return new LoginUserService(readUserPort,passwordHasher,tokenGenerator);
    }

    @Bean
    public DeleteUserUseCase deleteUserUseCase(DeleteUserPort deleteUserPort,
                                               ReadUserPort readUserPort,
                                               EventPublisherPort eventPublisherPort) {
        return new DeleteUserService(deleteUserPort,readUserPort,eventPublisherPort);
    }

}
