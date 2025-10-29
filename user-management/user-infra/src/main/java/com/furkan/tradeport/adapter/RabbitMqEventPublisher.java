package com.furkan.tradeport.adapter;

import com.furkan.tradeport.port.EventPublisherPort;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventPublisher implements EventPublisherPort {

    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange exchange;

    public RabbitMqEventPublisher(RabbitTemplate rabbitTemplate, TopicExchange exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }

    @Override
    public void publish(Object event) {
        String routingKey = event.getClass().getSimpleName();
        rabbitTemplate.convertAndSend(exchange.getName(), routingKey, event);
        System.out.println("Published event: " + routingKey);
    }
}
