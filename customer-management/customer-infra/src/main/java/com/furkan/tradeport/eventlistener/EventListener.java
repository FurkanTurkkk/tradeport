package com.furkan.tradeport.eventlistener;

import com.furkan.tradeport.adapter.CreateCustomerAdapter;
import com.furkan.tradeport.adapter.DeleteCustomerAdapter;
import com.furkan.tradeport.event.UserDeletedEvent;
import com.furkan.tradeport.event.UserRegisteredEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener {
    private final CreateCustomerAdapter adapter;
    private final DeleteCustomerAdapter deleteCustomerAdapter;

    public EventListener(CreateCustomerAdapter adapter, DeleteCustomerAdapter deleteCustomerAdapter) {
        this.adapter = adapter;
        this.deleteCustomerAdapter = deleteCustomerAdapter;
    }

    @RabbitListener(queues = "user-registered-queue")
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        String userId = event.userId();
        adapter.createCustomer(userId);
    }

    @RabbitListener(queues = "user-deleted-queue")
    public void handleUserDeletedEvent(UserDeletedEvent event) {
        String userId = event.userId();
        deleteCustomerAdapter.deleteCustomerByUserId(userId);
    }
}
