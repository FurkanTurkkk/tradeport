package com.furkan.tradeport.config;

import com.furkan.tradeport.port.CreateCustomerPort;
import com.furkan.tradeport.port.ReadCustomerPort;
import com.furkan.tradeport.port.UpdateCustomerPort;
import com.furkan.tradeport.service.CreateCustomerService;
import com.furkan.tradeport.service.ReadCustomerService;
import com.furkan.tradeport.service.UpdateCustomerService;
import com.furkan.tradeport.usecase.CreateCustomerUseCase;
import com.furkan.tradeport.usecase.ReadCustomerUseCase;
import com.furkan.tradeport.usecase.UpdateCustomerUseCase;
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

    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase(ReadCustomerPort readCustomerPort, UpdateCustomerPort updateCustomerPort) {
        return new UpdateCustomerService(readCustomerPort, updateCustomerPort);
    }
}
