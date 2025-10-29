package com.furkan.tradeport.config;

import com.furkan.tradeport.port.CreateCustomerPort;
import com.furkan.tradeport.port.ReadCustomerPort;
import com.furkan.tradeport.service.CreateCustomerService;
import com.furkan.tradeport.service.ReadCustomerService;
import com.furkan.tradeport.usecase.CreateCustomerUseCase;
import com.furkan.tradeport.usecase.ReadCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerApiConfig {

    @Bean
    public CreateCustomerUseCase createCustomerUseCase(CreateCustomerPort createCustomerPort) {
        return new CreateCustomerService(createCustomerPort);
    }

    @Bean
    public ReadCustomerUseCase readCustomerUseCase(ReadCustomerPort readCustomerPort) {
        return new ReadCustomerService(readCustomerPort);
    }
}
