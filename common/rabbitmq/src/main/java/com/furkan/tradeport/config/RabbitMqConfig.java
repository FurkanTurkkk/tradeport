package com.furkan.tradeport.config;

import com.furkan.tradeport.event.UserDeletedEvent;
import com.furkan.tradeport.event.UserRegisteredEvent;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange tradeportExchange() {
        return new TopicExchange("tradeport-exchange");
    }

    @Bean
    public Queue userRegisteredQueue() {
        return new Queue("user-registered-queue");
    }

    @Bean
    public Queue userDeletedQueue() {
        return new Queue("user-deleted-queue");
    }

    @Bean
    public Binding bindingUserRegistered(@Qualifier("userRegisteredQueue") Queue userRegisteredQueue, TopicExchange tradeportExchange) {
        return BindingBuilder.bind(userRegisteredQueue)
                .to(tradeportExchange)
                .with(UserRegisteredEvent.class.getSimpleName());
    }

    @Bean
    public Binding bindingUserDeleted(@Qualifier("userDeletedQueue") Queue userDeletedQueue, TopicExchange tradeportExchange) {
        return BindingBuilder.bind(userDeletedQueue)
                .to(tradeportExchange)
                .with(UserDeletedEvent.class.getSimpleName());
    }
}
